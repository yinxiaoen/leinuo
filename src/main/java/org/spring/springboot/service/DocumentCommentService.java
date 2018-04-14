package org.spring.springboot.service;

import org.spring.springboot.domain.DocumentCommentDTO;

import java.util.List;

/**
 * Created by Administrator on 2018/4/14.
 */
public interface DocumentCommentService {
    List<DocumentCommentDTO> queryDocumentCommentList(DocumentCommentDTO documentCommentDTO);

    void insertDocumentComment(DocumentCommentDTO documentCommentDTO);
}
