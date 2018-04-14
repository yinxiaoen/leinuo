package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.domain.DocumentCommentDTO;
import org.spring.springboot.service.DocumentCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/4/14.
 */
@RestController
@CrossOrigin
@RequestMapping("/documentComment")
public class DocumentCommentController{
    @Autowired
    private DocumentCommentService documentCommentService;

    @RequestMapping(value = "/document/queryDocumentCommentList", method = RequestMethod.POST)
    public Object queryDocumentCommentList(@RequestBody DocumentCommentDTO paramDTO) {
        List<DocumentCommentDTO> list =  documentCommentService.queryDocumentCommentList(paramDTO);

        return new Result("0", list);
    }

    @RequestMapping(value = "/document/insertDocumentComment", method = RequestMethod.POST)
    public Object insertDocumentComment(@RequestBody DocumentCommentDTO paramDTO) {
        documentCommentService.insertDocumentComment(paramDTO);
        return new Result("0", "");
    }



}
