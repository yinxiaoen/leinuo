package org.spring.springboot.service.impl;

import org.spring.springboot.dao.UserDao;
import org.spring.springboot.domain.UserDTO;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/31.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<UserDTO> loginUser(UserDTO userDTO) {
        return userDao.loginUser(userDTO);
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        userDao.registerUser(userDTO);
    }

    @Override
    public UserDTO queryUserById(Long userID) {
        return userDao.queryUserById(userID);
    }
}
