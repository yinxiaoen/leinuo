package org.spring.springboot.domain;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: zhoudapeng
 * Date: 2018/4/14
 */
@Data
public class ImageServerDTO {
    private Integer id;
    private String imageUrl;
    private String documentID;
    private String imageType;
}