package org.spring.springboot.domain;

import lombok.Data;

/**
 * Created by yinxiaoen on 2018/4/25.
 */
@Data
public class LawBookDTO {
    private Long id;
    private String title;
    private String bookUrl;
    private Integer pageNo;
    private Integer action;//1:启用
    private String ids;
    private String pageNos;
}
