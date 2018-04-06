package org.spring.springboot.domain;

import lombok.Data;

/**
 * Created by yinxiaoen on 2018/3/21.
 */
@Data
public class ProjectDocument {
    private Integer id;
    private String documentName;
    private String documentDesc;
    private String documentContent;
    private String documentCode;
    private String categoryName;
    private String categoryCode;
    private Integer categoryID;
    private Integer projectID ;
    private String projectCode;
    private String projectName;
    private String taxPrice;
    private Long finishDate;
    private String projectStatus;
    private Long actionTime;
    private Integer action;
    private Long createTime;
    private String createby;
    private String actionBy;
    private String documentPath;
    private String imagePath;
    private String htmlPath;
}
