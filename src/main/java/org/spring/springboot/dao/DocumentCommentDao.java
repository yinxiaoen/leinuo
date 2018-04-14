package org.spring.springboot.dao;

import org.spring.springboot.domain.DocumentCommentDTO;

import java.util.List;

/**
 * Created by yinxiaoen on 2018/4/13.
 */
public interface DocumentCommentDao {

    List<DocumentCommentDTO> queryDocumentCommentList(DocumentCommentDTO documentCommentDTO);

    void insertDocumentComment(DocumentCommentDTO documentCommentDTO);


}
