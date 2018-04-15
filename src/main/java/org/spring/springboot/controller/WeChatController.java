package org.spring.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.spring.springboot.common.Result;
import org.spring.springboot.config.wechat.WeChatProperties;
import org.spring.springboot.dao.RedisDao;
import org.spring.springboot.domain.UserDTO;
import org.spring.springboot.service.UserService;
import org.spring.springboot.utils.CommonUtils;
import org.spring.springboot.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    @Autowired
    RedisDao redisDao;
    @RequestMapping(value = "/getWebChatToken", method = RequestMethod.GET)
    public Object getWebChatToken(String code,String loginToken) {
        //添加参数
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appid", weChatProperties.getAppid());
        paramMap.put("secret", weChatProperties.getSecret());
        paramMap.put("code", code);
        paramMap.put("grant_type", weChatProperties.getGrant_type());
        HttpResponse response;
        String rep = null;
        UserDTO userDTO = new UserDTO();
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            response = HttpUtil.doGet(weChatProperties.getHost(), weChatProperties.getPath(), "get", headers,
                     paramMap);
            rep = EntityUtils.toString(response.getEntity());
            JSONObject obj= JSON.parseObject(rep);
            String accessToken = obj.getString("access_token");
            String openid = obj.getString("openid");
            userDTO =  getWeChatUser(accessToken,openid,"11111111",loginToken);
        } catch (Exception e) {
            String a =e.getMessage();
            System.out.print("");
        }
        return new Result("0", userDTO);
    }




    public UserDTO getWeChatUser(String token,String openID,String deviceID,String loginToken) {
        //添加参数
        Map<String, String> paramMap = new HashMap();
        paramMap.put("access_token", token);
        paramMap.put("openid", openID);
        HttpResponse response;
        String rep = null;
        UserDTO userDTO = new UserDTO();
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            response = HttpUtil.doGet(weChatProperties.getHost(), weChatProperties.getPathu(), "get", headers,
                    paramMap);
            rep = new String(EntityUtils.toString(response.getEntity(), "utf-8"));
            JSONObject obj= JSON.parseObject(rep);
            String name = obj.getString("nickname");
            String headImage = obj.getString("headimgurl");
            userDTO.setOpenid(openID);
            List<UserDTO> list = userService.webchatQuery(userDTO);
            UserDTO returnUserDTO = new UserDTO();
            if(!CommonUtils.isEmpty(list)){
                //String loginedOpenID = redisDao.getValue(openID);
                returnUserDTO = list.get(0);
                returnUserDTO.setToken(openID);
                userDTO = returnUserDTO;
            }else{
                userService.deleteUserByOpenID(userDTO.getOpenid());
                userDTO.setUserName(name);
                userDTO.setWeChatName(name);
                userDTO.setHeadImageUrl(headImage);
                userDTO.setOpenid(openID);
                userDTO.setDeviceID(openID);
                userDTO.setUserType(3);
                userDTO.setIsLogin(1);
                userDTO.setToken(UUID.randomUUID().toString());
                userService.registerUser(userDTO);
                //redisDao.setKey(userDTO.getToken(),userDTO.getOpenid());
            }
        } catch (Exception e) {
        }
        return userDTO;
    }
}
