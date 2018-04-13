package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.dao.UserDao;
import org.spring.springboot.domain.UserDTO;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/3/31.
 */
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/document/registerUser", method = RequestMethod.POST)
    public Object registerUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return new Result("0", "");
    }


    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Object login(@RequestBody UserDTO userDTO) {
        List<UserDTO> list = userService.loginUser(userDTO);
        if(list!=null && list.size()>0){
            return new Result("0", "");
        }else{
            return new Result("01", "");
        }
    }
}
