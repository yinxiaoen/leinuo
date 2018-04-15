package org.spring.springboot.service;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.UserDTO;

import java.util.List;

/**
 * Created by yinxiaone on 2018/3/31.
 */
public interface UserService {
    List<UserDTO> loginUser(UserDTO userDTO);
    void registerUser(UserDTO userDTO);
    UserDTO queryUserById(Long userID);
    void deleteUserByOpenID(@Param("openid") String openid);
    List<UserDTO> webchatQuery(UserDTO userDTO);
}
