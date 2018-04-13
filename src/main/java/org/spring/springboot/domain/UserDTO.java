package org.spring.springboot.domain;

import lombok.Data;

/**
 * Created by Administrator on 2018/3/31.
 */
@Data
public class UserDTO {
    private Long id;
    private String userName;
    private String passWord;
    private String telePhone;
    private String weChat;
    private String weChatName;
    private Long actionTime;
    private Long createTime;
    private Integer userType;
    private String deviceID;
    private Integer isLogin;
    private String headImageUrl;
    private String openid;
}
