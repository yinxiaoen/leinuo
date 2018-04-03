package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.spring.springboot.domain.UserDTO;

import java.util.List;

/**
 * Created by Administrator on 2018/3/31.
 */
@Mapper
public interface UserDao {
    List<UserDao> loginUser(UserDTO userDTO);
    void registerUser(UserDTO userDTO);
}
