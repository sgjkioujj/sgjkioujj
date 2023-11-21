package cn.memset.user.autoconfigure;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "cnmemset.ms-user")
public class MsUserConfiguration {

    /**
     * 微服务ms-user的url
     */
    private String url = "127.0.0.1:8080";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

