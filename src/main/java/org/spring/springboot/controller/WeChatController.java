package org.spring.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.spring.springboot.common.Result;
import org.spring.springboot.config.wechat.WeChatProperties;
import org.spring.springboot.domain.UserDTO;
import org.spring.springboot.service.UserService;
import org.spring.springboot.utils.CommonUtils;
import org.spring.springboot.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinxiaoen on 2018/4/10.
 */
@RestController
@CrossOrigin
@RequestMapping("/wechat")
public class WeChatController {
    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getWebChatToken", method = RequestMethod.GET)
    public Object getWebChatToken(String code) {
        //添加参数
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appid", weChatProperties.getAppid());
        paramMap.put("secret", weChatProperties.getSecret());
        paramMap.put("code", code);
        paramMap.put("grant_type", weChatProperties.getGrant_type());
        HttpResponse response;
        String rep = null;
        try {
            response = HttpUtil.doGet(weChatProperties.getHost(), weChatProperties.getPath(), "get", null,
                     paramMap);
            rep = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
        }
        return new Result("0", rep);
    }



    @RequestMapping(value = "/getWeChatUser", method = RequestMethod.GET)
    public Object getWeChatUser(String token,String openID,String deviceID) {
        //添加参数
        Map<String, String> paramMap = new HashMap();
        paramMap.put("access_token", token);
        paramMap.put("openid", openID);
        HttpResponse response;
        String rep = null;
        try {
            response = HttpUtil.doGet(weChatProperties.getHost(), weChatProperties.getPathu(), "get", null,
                    paramMap);
            rep = EntityUtils.toString(response.getEntity());
            JSONObject obj= JSON.parseObject(rep);
            String name = obj.getString("nickname");
            String headImage = obj.getString("headimgurl");
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(name);
            userDTO.setWeChatName(headImage);
            userDTO.setOpenid(openID);
            userDTO.setDeviceID(deviceID);
            userDTO.setUserType(3);
            userDTO.setIsLogin(1);
            List<UserDTO> list = userService.loginUser(userDTO);
            if(CommonUtils.isEmpty(list)){
                userService.registerUser(userDTO);
                UserDTO user= userService.queryUserById(userDTO.getId());
            }
        } catch (Exception e) {
        }
        return new Result("0", rep);
    }
}
