package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.dao.RedisDao;
import org.spring.springboot.domain.ProjectDocument;
import org.spring.springboot.domain.SendEmailDTO;
import org.spring.springboot.domain.UserDTO;
import org.spring.springboot.service.ProjectDocumentService;
import org.spring.springboot.service.UserService;
import org.spring.springboot.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 * Created by yinxioaen on 2018/5/6.
 */
@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private JavaMailSender mailSender; //自动注入的Bean
    @Autowired
    private UserService userService;
    @Autowired
    RedisDao redisDao;
    @Autowired
    private ProjectDocumentService projectDocumentService;
    @Value("${spring.mail.username}")
    private String Sender; //读取配置文件中的参数
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public Object sendAttachmentsMail(@RequestBody SendEmailDTO sendEmailDTO) {
        UserDTO user = userService.queryUserById(sendEmailDTO.getUserID());
        if(!CommonUtils.isBlank(user.getEmail())){
            return new Result("01", "请绑定邮箱后在使用此功能",false,"");
        }
        ProjectDocument paramDTO  =new ProjectDocument();
        paramDTO.setId(sendEmailDTO.getDocumentID());
        List<ProjectDocument> list =  projectDocumentService.findAllDocumentAndProject(paramDTO);
        if(CommonUtils.isEmpty(list)){
            return new Result("01", "文件地址不正确",false,"");
        }
        MimeMessage message = null;
        try {
            String key = list.get(0).getDocumentRealName()+sendEmailDTO.getUserID();
            if(redisDao.isHaveKey(key)){
                return new Result("01", "已发送过，请稍后再试",false,"");
            }
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Sender);
            helper.setTo(user.getEmail());
            helper.setSubject(list.get(0).getDocumentRealName());
            helper.setText(list.get(0).getDocumentRealName());
            //注意项目路径问题，自动补用项目路径
            FileSystemResource file = new FileSystemResource(new File(list.get(0).getDocumentPath()));
            //加入邮件
            helper.addAttachment(list.get(0).getDocumentRealName(), file);
            redisDao.setKeyByMin(key, "发送邮件给"+user.getUserName()+" 邮件："+list.get(0).getDocumentRealName());
            mailSender.send(message);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new Result("0", "");
    }



}
