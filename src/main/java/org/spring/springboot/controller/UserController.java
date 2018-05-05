package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.config.sms.SmsProperties;
import org.spring.springboot.dao.RedisDao;
import org.spring.springboot.domain.UserDTO;
import org.spring.springboot.service.UserService;
import org.spring.springboot.utils.CommonUtils;
import org.spring.springboot.utils.Md5tools;
import org.spring.springboot.utils.TencentSmsSender;
import org.spring.springboot.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
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
        /**
         * 判断验证码是否正确
         */
        if(CommonUtils.isBlank(userDTO.getHeadImageUrl())){
            userDTO.setHeadImageUrl(CommonUtils.getHeadImg());
        }
        Boolean  codeIsRight = CommonUtils.isHaveSMSLoginID(userDTO.getSmsCode());
        if(!codeIsRight){
            return new Result("01", "验证码不正确,请重新输入",false,"");
        }
        UserDTO userDTO1 =new UserDTO();
        userDTO1.setTelePhone(userDTO.getTelePhone());
        List<UserDTO>list=userService.queryUserByTel(userDTO);
        if(CommonUtils.isEmpty(list)){
            userService.registerUser(userDTO);
            return new Result("0", userDTO);
        }else{
            if(list.get(0).getUserType()==2){
                userDTO.setId(list.get(0).getId());
                userDTO.setUserType(0);
                userService.updateUserInformation(userDTO);
                return new Result("0", userDTO);
            }else{
                return new Result("01", "该手机号已经注册过",false,"");
            }
        }
    }

    @RequestMapping(value = "/user/telQuickLogin", method = RequestMethod.POST)
    public Object telQuickLogin(@RequestBody UserDTO userDTO) {
        Boolean  codeIsRight = CommonUtils.isHaveSMSLoginID(userDTO.getSmsCode());
        if(!codeIsRight){
            return new Result("01", "验证码不正确",false,"");
        }
        if(CommonUtils.isBlank(userDTO.getToken())){
            String id = redisDao.getValue(userDTO.getToken());
            if(CommonUtils.isBlank(id)){
                UserDTO user = userService.queryUserById(Long.valueOf(id));
                if(null == user){
                    return new Result("01", "token 已经失效，请重新登陆");
                }else{
                    return new Result("0", user);
                }
            }else{
                return new Result("01", "token 已经失效，请重新登陆");
            }
        }else {
            List<UserDTO> userList = userService.queryUserByTel(userDTO);
            if(CommonUtils.isEmpty(userList)){
                userDTO.setHeadImageUrl(CommonUtils.getHeadImg());
                userDTO.setUserName("游侠"+ToolsUtils.getRandomString(4));
                userDTO.setPassWord(ToolsUtils.getRandomString(8));
                /**
                 * 手机快速登陆，没有账号就注册  userType = 2
                 */
                userDTO.setUserType(2);
                userService.registerUser(userDTO);
                return new Result("0", userDTO);
            }else{
                return new Result("0", userList.get(0));
            }

        }
    }

    /**
     * 判断验证码是否正确
     * @param userDTO
     * @return
     */
    public Object isSmsRight(UserDTO userDTO) {
            Boolean  codeIsRight = CommonUtils.isHaveSMSLoginID(userDTO.getSmsCode());
          if(codeIsRight){
              return new Result("0", "");
          }else{
              return new Result("01", "验证码不正确",false,"");
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


    @RequestMapping(value = "/user/updateUserInformation", method = RequestMethod.POST)
    public Object updateUserInformation(@RequestBody UserDTO userDTO) {
        try {
        userService.updateUserInformation(userDTO);
        return new Result("0", "");
        } catch (Exception e) {
            // 网络IO错误
            e.printStackTrace();
            return new Result("01", "修改失败");
        }
    }


    public static void mainTest(){
        BigDecimal big = new BigDecimal(2.1430000);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        System.out.println(nf.format(big));

    }



}
