package org.spring.springboot.utils;
import org.spring.springboot.config.image.ImageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * 发送文件到文件服务器 spring-hessian方式发送
 *
 * @author Administrator
 */
@Component
public class ServiceCallByHessian {
    @Autowired
    private ImageProperties imageProperties;
    /**
     * 根据FILE_URL地址以hessian方式发送
     *
     * @return 返回GozapServiceResult对象
     * @throws IOException
     */


}
