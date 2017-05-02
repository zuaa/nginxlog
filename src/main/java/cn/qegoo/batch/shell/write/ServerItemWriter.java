package cn.qegoo.batch.shell.write;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import cn.qegoo.batch.shell.dto.NginxLog;

public class ServerItemWriter implements ItemWriter<NginxLog> {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void write(List<? extends NginxLog> arg0) throws Exception {

        final String sql = "INSERT INTO \"main\".\"nginxLog\" (\"ip\", \"dataString\", \"method\"," +
                " \"url\", \"httpversion\", \"code\", \"pageLength\", \"viewer\")" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        for (NginxLog item : arg0) {
            if(item!=null) {
                System.out.print("-");
                int count = jdbcTemplate.update(sql,
                        new Object[]{item.getIp(), item.getDataString(), item.getMethod(), item.getUrl(),
                                item.getHttpversion(), item.getCode(), item.getPageLength(), item.getViewer()});
            }
        }

    }
}
