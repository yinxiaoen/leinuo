package org.spring.springboot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/4/1.
 */
@Data
@Component
public class ConfigBean {
    @Value("${tbl_surf_glb_mul_file_path}")
    private String tbl_surf_glb_mul_file_path;
    @Value("${tbl_surf_html}")
    private String tbl_surf_html;
    @Value("${tbl_surf_glb_mul_headimage_file_path}")
    private String headImage;
    @Value("${tbl_surf_real_image}")
    private String realImage;
}
