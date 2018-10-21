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

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.List;
import java.util.Properties;

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
                return new Result("01", "已发送过，请5分钟后用再试",false,"");
            }
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Sender);
            helper.setTo(user.getEmail());
            helper.setSubject("产权圈向您推荐"+list.get(0).getDocumentName());
            if(CommonUtils.isBlank(list.get(0).getDocumentContent())){
                helper.setText(list.get(0).getDocumentContent(),true);
            }else{
                helper.setText("尊敬的".concat(user.getUserName()).concat("   ").concat("请您关注").concat(list.get(0).getDocumentName()));
            }

            //注意项目路径问题，自动补用项目路径
            //String filePath = MimeUtility.encodeText(list.get(0).getDocumentPath());
            String filePath = list.get(0).getDocumentPath();
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String suffix = filePath.substring(filePath.lastIndexOf("."));
            //加入邮件
            helper.addAttachment(System.currentTimeMillis()+suffix, file);
            redisDao.setKeyByMin(key, "发送邮件给"+user.getUserName()+" 邮件："+list.get(0).getDocumentRealName());
            mailSender.send(message);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new Result("0", "");
    }

    @RequestMapping(value = "/sendEmailTest", method = RequestMethod.POST)
    public Object sendAttachmentsMailTest(@RequestBody SendEmailDTO sendEmailDTO) {
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

            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Sender);
            helper.setTo("1293954315@qq.com");
            helper.setSubject("测试");
            helper.setText("测试123");
            //注意项目路径问题，自动补用项目路径
            String filePat1h = MimeUtility.encodeText("C:\\Users\\Administrator\\Desktop\\新建文件夹 (9)\\12345.docx");
            String filePath = "C:\\Users\\Administrator\\Desktop\\新建文件夹 (9)\\12345.docx";

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String suffix = filePath.substring(filePath.lastIndexOf("."));
            //加入邮件
            helper.addAttachment("测试1234"+suffix, file);

            mailSender.send(message);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new Result("0", "");
    }


    public static void main( String[] args ) throws MessagingException
    {

        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        /*
         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
         * mail.user / mail.from
         */
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.debug", "true");//遇到最多的坑就是下面这行，不加要报“A secure connection is requiered”错。
            props.put("mail.smtp.starttls.enable", "true");
        // 发件人的账号
        props.put("mail.user", "445847992@qq.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "irqllklwmgddbhaj");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人
        InternetAddress to = new InternetAddress("1293954315@qq.com");
        message.setRecipient(MimeMessage.RecipientType.TO, to);

        // 设置抄送，抄送和密送如果不写正确的地址也要报错。最好注释不用。
//        InternetAddress cc = new InternetAddress("");
//        message.setRecipient(RecipientType.CC, cc);
//
//        // 设置密送，其他的收件人不能看到密送的邮件地址
//        InternetAddress bcc = new InternetAddress("");
//        message.setRecipient(RecipientType.CC, bcc);

        // 设置邮件标题
        message.setSubject("JAVA测试邮件");

        // 设置邮件的内容体
        message.setContent("<a href='http://www.XXX.org'>测试的邮件</a>", "text/html;charset=UTF-8");

        // 发送邮件
        Transport.send(message);

    }
}
