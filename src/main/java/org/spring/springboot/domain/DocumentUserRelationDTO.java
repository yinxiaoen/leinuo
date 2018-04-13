package org.spring.springboot.domain;

import lombok.Data;

/**
 * Created by Administrator on 2018/4/13.
 */
@Data
public class DocumentUserRelationDTO {
    private Long documentID;
    private Long userID;
    private Integer isAttention;
    private Integer isRead;
}
