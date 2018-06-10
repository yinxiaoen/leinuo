package org.spring.springboot.domain;

import lombok.Data;

/**
 * Created by Administrator on 2018/4/13.
 */
@Data
public class DocumentUserRelationDTO {
    private Long documentID;
    private Long userID;
    private Integer isAttention;// 1是关注了 0 没有关注
    private Integer isRead;//0 是没有阅读 1，是阅读了
    private String documentName;
    private String documentCode;
    private String categoryName;
    private String projectCode;
    private String projectName;
    private String taxPrice;
    private Long finishDate;
    private Long id;
    private Long actionTime;
    private String actionTimeFormat;
}
