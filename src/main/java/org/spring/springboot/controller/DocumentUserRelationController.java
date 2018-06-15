package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.domain.DocumentUserRelationDTO;
import org.spring.springboot.domain.UserDTO;
import org.spring.springboot.service.DocumentUserRelationService;
import org.spring.springboot.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by yinxiaoen on 2018/4/13.
 */
@RestController
@CrossOrigin
@RequestMapping("/documentUserRelation")
public class DocumentUserRelationController {
    @Autowired
    private DocumentUserRelationService documentUserRelationService;

    @RequestMapping(value = "/document/queryProjectAndDocument", method = RequestMethod.POST)
    public Object queryProjectAndDocument(@RequestBody DocumentUserRelationDTO paramDTO) {
        List<DocumentUserRelationDTO> list =  documentUserRelationService.queryDocumentUserRelation(paramDTO);
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            DateFormat sdfTime = new SimpleDateFormat("yyyyMMddhhmmss");

            list.forEach(e->{
                try {
                    String time = format.format(sdfTime.parse(String.valueOf(e.getActionTime())));
                    e.setActionTimeFormat(time);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            });
            return new Result("0", list);
        } catch (Exception e) {
            // 网络IO错误
            e.printStackTrace();
            return new Result("01", "查询失败");
        }
    }

    @RequestMapping(value = "/document/insertDocumentUserRelation", method = RequestMethod.POST)
    public Object insertDocumentUserRelation(@RequestBody DocumentUserRelationDTO paramDTO) {
        DocumentUserRelationDTO newDoc = new DocumentUserRelationDTO();
        newDoc.setUserID(paramDTO.getUserID());
        newDoc.setDocumentID(paramDTO.getDocumentID());
        List<DocumentUserRelationDTO> list = documentUserRelationService.queryDocumentUserRelation(newDoc);
        if(CommonUtils.isEmpty(list)){
            documentUserRelationService.insertDocumentUserRelation(paramDTO);
        }else{
            paramDTO.setId(list.get(0).getId());
            documentUserRelationService.updateDocumentUserRelation(paramDTO);
        }
        return new Result("0", "");
    }

    @RequestMapping(value = "/document/updateDocumentUserRelation", method = RequestMethod.POST)
    public Object updateDocumentUserRelation(@RequestBody DocumentUserRelationDTO paramDTO) {
        documentUserRelationService.updateDocumentUserRelation(paramDTO);
        return new Result("0", "");
    }

    @RequestMapping(value = "/document/queryDocumentUserRelationCountsByID", method = RequestMethod.GET)
    public Object updateDocumentUserRelation(Long documentID) {
        Integer count = documentUserRelationService.queryDocumentUserRelationCountsByID(documentID);
        return new Result("0", count);
    }

}
