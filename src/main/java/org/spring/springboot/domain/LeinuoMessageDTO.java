package org.spring.springboot.domain;

import lombok.Data;

/**
 * Created by YINXIAOEN on 2018/5/10.
 */
@Data
public class LeinuoMessageDTO {
    private Long id;
    private Long userID;
    private String messageContent;
    private String messageTitle;
    private Long documentID;
    private Integer isRead;
    private  Long createTime;
    private  Long actionTime;
    private String userIDs;
}
