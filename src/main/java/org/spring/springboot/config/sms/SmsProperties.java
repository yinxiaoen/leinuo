package org.spring.springboot.config.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by yinxiaoen on 2018/4/25.
 */
@Data
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {
    private Integer appid;
    private String appkey;
}
