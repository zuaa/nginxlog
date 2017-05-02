package cn.qegoo.batch.shell.reader;

import cn.qegoo.batch.shell.AnalysisHtml;
import cn.qegoo.batch.shell.dto.NginxLog;

import org.springframework.batch.item.file.LineMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 读取任务(数据库任务表，本地文件，供应商文件)
 * Created by xcf
 * 2016/7/27.
 */
public class TaskRowMapper implements LineMapper<NginxLog> {


    @Override
    public NginxLog mapLine(String s, int i) throws Exception {
//        System.out.println("这里处理原装数据，处理"+s);

        String model = "{{ip}} - - [{{dataString}}] {{method}} {{url}} {{httpversion}} {{code}} {{pageLength}} {{resourceUrl}} {{viewer}}";

        NginxLog log=null;
         try{
             AnalysisHtml _AnalysisHtml =new AnalysisHtml();
            Map<String, String> remap = _AnalysisHtml.getInfo(s,model);
              log=new NginxLog();
            log.setIp(remap.get("ip"));
            log.setDataString(remap.get("dataString"));
            log.setCode(remap.get("code"));
            log.setPageLength(remap.get("pageLength"));
            log.setViewer(remap.get("viewer"));
            log.setMethod(remap.get("method"));
            log.setUrl(remap.get("url"));
            log.setHttpversion(remap.get("httpversion"));
            return log;
        }catch (Exception e){

        }

        return log;
    }
}
