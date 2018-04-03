package org.spring.springboot.service;

import org.spring.springboot.dao.UserDao;
import org.spring.springboot.domain.UserDTO;

import java.util.List;

/**
 * Created by yinxiaone on 2018/3/31.
 */
public interface UserService {
    List<UserDao> loginUser(UserDTO userDTO);
    void registerUser(UserDTO userDTO);
}
