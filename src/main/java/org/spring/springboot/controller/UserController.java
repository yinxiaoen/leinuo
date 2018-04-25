package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.config.sms.SmsProperties;
import org.spring.springboot.dao.RedisDao;
import org.spring.springboot.domain.UserDTO;
import org.spring.springboot.service.UserService;
import org.spring.springboot.utils.CommonUtils;
import org.spring.springboot.utils.Md5tools;
import org.spring.springboot.utils.TencentSmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/3/31.
 */
@RestController
@CrossOrigin
@RequestMapping("/leinuo")
public class UserController {
    @Autowired
    private SmsProperties smsProperties;
    @Autowired
    private UserService userService;
    @Autowired
    RedisDao redisDao;
    @RequestMapping(value = "/user/registerUser", method = RequestMethod.POST)
    public Object registerUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return new Result("0", "");
    }


    @RequestMapping(value = "/user/isSmsRight", method = RequestMethod.POST)
        public Object isSmsRight(@RequestBody UserDTO userDTO) {
            Boolean  codeIsRight = CommonUtils.isHaveSMSLoginID(userDTO.getSmsCode());
          if(codeIsRight){
              return new Result("0", "");
          }else{
              return new Result("01", "");
          }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Object login(@RequestBody UserDTO userDTO) {
        if(CommonUtils.isBlank(userDTO.getToken())){
            String id = redisDao.getValue(userDTO.getToken());
            UserDTO user = userService.queryUserById(Long.valueOf(id));
            if(null == user){
                return new Result("01", "token 已经失效，请重新登陆");
            }else{
                return new Result("0", user);
            }
        }else {
            List<UserDTO> list = userService.loginUser(userDTO);
            if (list != null && list.size() > 0) {
                String token = Md5tools.MD5(list.get(0).getPassWord());
                redisDao.setKey(token, list.get(0).getId().toString());
                list.get(0).setToken(token);
                return new Result("0", list.get(0));
            } else {
                return new Result("01", "登录失败");
            }
        }
    }

    @RequestMapping(value = "/user/smsToLogin", method = RequestMethod.POST)
    public Object smsToLogin(@RequestBody UserDTO userDTO) {
        try {
            String smsId = CommonUtils.getSMSLoginID();
            String content ="您的验证码是: "+smsId;
            new TencentSmsSender(smsProperties.getAppid(), smsProperties.getAppkey()).sendMsg("86",userDTO.getTelePhone(),content);
            return new Result("0", "");
        } catch (Exception e) {
            // 网络IO错误
            e.printStackTrace();
            return new Result("01", "获取验证吗失败");
        }
    }



}
