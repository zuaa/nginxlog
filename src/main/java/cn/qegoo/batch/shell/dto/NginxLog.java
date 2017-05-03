package cn.qegoo.batch.shell.dto;

/**
 * 任务实体类
 */
public class NginxLog {

    private Integer id;
    private String ip;
    private String dataString;
    private String method;
    private String url;
    private String httpversion;
    private String code;
    private String pageLength;
    private String viewer;

    public String getViewer() {
        return viewer;
    }

    public void setViewer(String viewer) {
        this.viewer = viewer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpversion() {
        return httpversion;
    }

    public void setHttpversion(String httpversion) {
        this.httpversion = httpversion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPageLength() {
        return pageLength;
    }

    public void setPageLength(String pageLength) {
        this.pageLength = pageLength;
    }
}
