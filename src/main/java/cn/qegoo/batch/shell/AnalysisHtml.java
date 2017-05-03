package cn.qegoo.batch.shell;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuf on 2017/4/19.
 */
public class AnalysisHtml {
    public static void main(String[] args) {
        AnalysisHtml _AnalysisHtml = new AnalysisHtml();
        String html = "111.206.221.35 - - [20/Apr/2017:00:04:01 +0800] \"GET /saveLog?localUrl=http%3A%2F%2Fwww.qegoo.cn%2Fsearch%3Fpart%3DLB1832V-" +
                "TRM-E&originUrl=&version=v1&userName=&availWidth=375&availHeight=667 HTTP" +
                "/1.1\" 301 284 \"http://www.qegoo.cn/search?part=LB1832V-TRM-E\" \"Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1 (compatible; Baiduspider-render/2.0; +http://www.baidu.com/search/spider.html)\"";
        String model = "{{ip}} - - [{{dataString}}] \"{{method}} {{url}} {{httpversion}}\" {{code}} {{pageLength}} \"{{resourceUrl}}\" \"{{viewer}}\"";
        //解析模板

        Map<String, String> map = _AnalysisHtml.getInfo(html, model);
        System.out.println(map);
    }

    private List<String> getModelValue(String model) {
        List<String> list = new ArrayList<>();
        String[] models = model.split("}}");
        for (String str : models) {
//            System.out.println(str);
            String regex = "\\{\\{[\\s\\S]*\\}\\}";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str + "}}");
            if (m.find()) {
//                System.out.println(m.group());
                list.add(m.group());
            }
        }
        return list;
    }

    /**
     * 根据model处理html资料
     */
    public Map<String, String> getInfo(String html, String model) {
        List<String> modelList = getModelValue(model);
        List<String> models = new ArrayList<>();
        models.addAll(modelList);
        System.out.println(models);
        List<String> list = new ArrayList<>();
        getHtmlArr(list, model, modelList);
        return getInfo(html, list, models);
    }

    public Map<String, String> getInfo(String html, List<String> list, List<String> modelList) {
        Map<String, String> map = new LinkedHashMap<>();
        List<String> infoList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                String tagHead = list.get(i);
                String tagFoot = list.get(i + 1);
                String info = "";
                if (tagFoot == null || "".equals(tagFoot.trim())) {
                    if (tagHead == null || " ".equals(tagHead)) {
                        html = html.trim();
                    } else {
                        html = html.substring(html.indexOf(tagHead) + tagHead.length(), html.length());
//                        html = html.replace(tagHead, "");
                    }
                    if (i == list.size() - 2) {
                        info = html.substring(0, html.length());
                    } else {
                        String[] tempStrArr = html.split(" ");
                        info = tempStrArr[0];
                        html = html.replace(info + tagFoot, "");
                    }

                } else {
                    if (tagHead == null || " ".equals(tagHead)) {
                        info = html.substring(0, html.indexOf(tagFoot));
                        html = html.replace(info, "");
                    } else {
                        if (i == list.size() - 2) {
                            info = html.substring(html.indexOf(tagHead) + tagHead.length(), html.length()).replace(tagFoot, "");
                        } else {
                            info = html.substring(html.indexOf(tagHead) + tagHead.length(), html.indexOf(tagFoot));
                            html = html.replace(tagHead + info, "");
                        }
                    }
                }

                infoList.add(info);
            }
        }

        for (int i = 0; i < modelList.size(); i++) {
            String key = modelList.get(i).replace("{", "").replace("}", "");
            String value = infoList.get(i);
            map.put(key, value);
        }

        return map;
    }

    private void getHtmlArr(List<String> list, String model, List<String> modelStrList) {
        if (modelStrList.size() > 0) {
            String modelStr = modelStrList.get(0).replace("{", "").replace("}", "");
            modelStr = "\\{\\{" + modelStr + "\\}\\}";
            String[] models = model.split(modelStr);

            for (int i = 0; i < models.length; i++) {
                if (i == 0) {
                    list.add(models[0]);
                    if (modelStrList.size() == 1) {
                        if (models.length == 1) {
                            list.add("");
                        } else {
                            list.add(models[1]);
                        }
                    }
                    modelStrList.remove(0);
                } else {
                    getHtmlArr(list, models[1], modelStrList);
                }

            }
        }

    }
}
