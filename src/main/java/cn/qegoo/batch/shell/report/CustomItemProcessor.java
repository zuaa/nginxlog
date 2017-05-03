package cn.qegoo.batch.shell.report;

import org.springframework.batch.item.ItemProcessor;

import cn.qegoo.batch.shell.dto.NginxLog;


public class CustomItemProcessor implements ItemProcessor<NginxLog, NginxLog> {

    @Override
    public NginxLog process(NginxLog task) throws Exception {
//        System.out.println("2-----这里处理数据");

        return task;
    }


}
