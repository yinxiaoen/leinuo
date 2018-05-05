package org.spring.springboot.service;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.DocumentUserRelationDTO;

import java.util.List;

/**
 * Created by Administrator on 2018/4/13.
 */
public interface DocumentUserRelationService {

    List<DocumentUserRelationDTO> queryDocumentUserRelation(DocumentUserRelationDTO documentUserRelationDTO);

    void insertDocumentUserRelation(DocumentUserRelationDTO documentUserRelationDTO);

    void updateDocumentUserRelation(DocumentUserRelationDTO documentUserRelationDTO);

    Integer queryDocumentUserRelationCountsByID(@Param("documentID") Long documentID);

}
