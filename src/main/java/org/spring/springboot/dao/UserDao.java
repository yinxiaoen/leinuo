package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.UserDTO;

import java.util.List;

/**
 * Created by Administrator on 2018/3/31.
 */
@Mapper
public interface UserDao {
    List<UserDTO> loginUser(UserDTO userDTO);
    List<UserDTO> webchatQuery(UserDTO userDTO);

    List<UserDTO> queryUserByTel(UserDTO userDTO);
    int registerUser(UserDTO userDTO);
    UserDTO queryUserById(@Param("id") Long id);
    void deleteUserByOpenID(@Param("openid") String openid);
    void updateUserInformation(UserDTO userDTO);
}
