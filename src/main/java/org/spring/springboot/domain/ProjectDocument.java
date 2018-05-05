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
    private Integer categoryID;  //1 企业产股权，2企业增资扩股 3实物资产 4 网络竞价 5 法律汇编 6 经典案例 7 山东磊诺 8提示文档
    private Integer projectID ;  //1代表济南  2 代表北京  3代表 上海
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
    private String documentRealName;
    private String documentPath;
    private String imagePath;
    private String htmlPath;
    private String  categoryIDs;
}
