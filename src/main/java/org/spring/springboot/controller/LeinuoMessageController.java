package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.domain.LeinuoMessageDTO;
import org.spring.springboot.service.LeinuoMessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinxiaoen on 2018/5/10.
 */
@RestController
@CrossOrigin
@RequestMapping("/message")
public class LeinuoMessageController {
    @Autowired
    private LeinuoMessageService messageService;
    @RequestMapping(value = "/queryMessageByUser", method = RequestMethod.POST)
    public Object queryMessageByUser(@RequestBody LeinuoMessageDTO leinuoMessageDTO) {
        List<LeinuoMessageDTO> list =  messageService.queryMessageByUserID(leinuoMessageDTO);
        return new Result("0", list);
    }

    @RequestMapping(value = "/queryMessageCounts", method = RequestMethod.POST)
    public Object queryMessageCounts(@RequestBody LeinuoMessageDTO leinuoMessageDTO) {
        Integer count =  messageService.queryMessageCount(leinuoMessageDTO);
        return new Result("0", count);
    }

    @RequestMapping(value = "/insertMessage", method = RequestMethod.POST)
    public Object insertMessage(@RequestBody LeinuoMessageDTO leinuoMessageDTO) {
        String [] userIDs = leinuoMessageDTO.getUserIDs().split(",");
        List<LeinuoMessageDTO> list =new ArrayList();
        for(String userId:userIDs){
            LeinuoMessageDTO LeinuoMessageDTOCopy =new LeinuoMessageDTO();
            BeanUtils.copyProperties(leinuoMessageDTO,LeinuoMessageDTOCopy);
            LeinuoMessageDTOCopy.setUserID(Long.valueOf(userId));
            list.add(LeinuoMessageDTOCopy);
        }
        messageService.addMessageByUser(list);
        return new Result("0", "");
    }

    @RequestMapping(value = "/deleteMessage", method = RequestMethod.GET)
    public Object queryMessageCounts(String ids) {
        messageService.deleteMessageByIDs(ids);
        return new Result("0", "");
    }


    @RequestMapping(value = "/queryMessageByID", method = RequestMethod.GET)
    public Object queryMessageCounts(Long id) {
        LeinuoMessageDTO leinuoMessageDTO =  messageService.queryMessageByID(id);
        return new Result("0", leinuoMessageDTO);
    }


    @RequestMapping(value = "/updateMessage", method = RequestMethod.POST)
    public Object updateMessage(@RequestBody LeinuoMessageDTO leinuoMessageDTO) {
        messageService.updateMessage(leinuoMessageDTO);
        return new Result("0", "");
    }

}
