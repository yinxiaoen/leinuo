package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.domain.UserDTO;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/3/31.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/document/registerUser", method = RequestMethod.POST)
    public Object registerUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return new Result("000", "");
    }
}
