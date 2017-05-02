package cn.qegoo.batch.shell.report;

import cn.qegoo.batch.shell.dto.NginxLog;
import org.springframework.batch.item.ItemProcessor;


public class CustomItemProcessor implements ItemProcessor<NginxLog, NginxLog> {

    @Override
    public NginxLog process(NginxLog task) throws Exception {
//        System.out.println("2-----这里处理数据");

        return task;
    }


}
