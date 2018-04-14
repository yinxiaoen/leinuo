package org.spring.springboot.service.impl;

import org.spring.springboot.dao.DocumentCommentDao;
import org.spring.springboot.domain.DocumentCommentDTO;
import org.spring.springboot.service.DocumentCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/4/14.
 */
@Service
public class DocumentCommentServiceImpl implements DocumentCommentService{
    @Autowired
    private DocumentCommentDao documentCommentDao;
    @Override
    public List<DocumentCommentDTO> queryDocumentCommentList(DocumentCommentDTO documentCommentDTO) {
        return documentCommentDao.queryDocumentCommentList(documentCommentDTO);
    }

    @Override
    public void insertDocumentComment(DocumentCommentDTO documentCommentDTO) {
        documentCommentDao.insertDocumentComment(documentCommentDTO);
    }
}
