package org.spring.springboot.config.wechat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/4/10.
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatProperties {
    private String host;
    private String path;
    private String appid;
    private String secret;
    private String code;
    private String grant_type;
    private String pathu;

}
