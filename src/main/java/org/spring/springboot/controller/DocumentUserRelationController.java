package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.domain.DocumentUserRelationDTO;
import org.spring.springboot.service.DocumentUserRelationService;
import org.spring.springboot.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return new Result("0", list);
    }

    @RequestMapping(value = "/document/insertDocumentUserRelation", method = RequestMethod.POST)
    public Object insertDocumentUserRelation(@RequestBody DocumentUserRelationDTO paramDTO) {
        List<DocumentUserRelationDTO> list = documentUserRelationService.queryDocumentUserRelation(paramDTO);
        if(CommonUtils.isEmpty(list)){
            documentUserRelationService.insertDocumentUserRelation(paramDTO);
        }else{
            documentUserRelationService.updateDocumentUserRelation(list.get(0));
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
