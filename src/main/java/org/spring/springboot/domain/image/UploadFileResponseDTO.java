package org.spring.springboot.domain.image;

import lombok.Data;

/**
 * Created by Administrator on 2016/9/8.
 */
@Data
public class UploadFileResponseDTO {
    private String code;
    private String msg;//000
    private String url;
    private Boolean success;
}
