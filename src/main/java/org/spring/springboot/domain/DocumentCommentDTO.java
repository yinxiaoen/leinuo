package org.spring.springboot.domain;

import lombok.Data;

/**
 * Created by yinxiaoen on 2018/4/13.
 */
@Data
public class DocumentCommentDTO {
    private  Long id;
    private  Long documentID;
    private  Long userID;
    private  String userName;
    private  String comment;
    private  Long createTime;
    private  Long actionTime;
}
