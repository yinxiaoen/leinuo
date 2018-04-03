package org.spring.springboot.utils;
import com.caucho.hessian.client.HessianProxyFactory;
import com.gozap.service.client.GozapServiceApp;
import com.gozap.service.client.GozapServiceResult;
import com.gozap.service.client.dfs.GozapImageService;
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
    public GozapServiceResult sendByHessian( String fileExt, byte[] fileByte) throws IOException {
        String url = imageProperties.getUpLoadHosts();
        HessianProxyFactory factory = new HessianProxyFactory();
        GozapImageService service = (GozapImageService) factory.create(GozapImageService.class, url);
        return service.uploadImage(GozapServiceApp.HUALALA, fileByte, fileExt);

    }

}
