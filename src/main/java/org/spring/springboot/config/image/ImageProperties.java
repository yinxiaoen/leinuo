package org.spring.springboot.config.image;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/10/8.
 */
@Data
@Component
@ConfigurationProperties(prefix = "image")
public class ImageProperties {
    private String downLoadHosts;
    private String upLoadHosts;
}
