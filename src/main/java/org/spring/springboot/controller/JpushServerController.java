package org.spring.springboot.controller;
import org.spring.springboot.common.Result;
import org.spring.springboot.utils.JpushServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * Created by yinxiaoen on 2018/5/10.
 */
@RestController
@CrossOrigin
@RequestMapping("/pushMessage")
public class JpushServerController {
    @Autowired
    private JpushServer jpushServer;
    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    public Object sendMessage(String alias,String message) {
        jpushServer.jiguangPush(alias,message);
        return new Result("0", "");
    }

}
