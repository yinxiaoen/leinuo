package org.spring.springboot.service.impl;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.dao.DocumentUserRelationDao;
import org.spring.springboot.domain.DocumentUserRelationDTO;
import org.spring.springboot.service.DocumentUserRelationService;
import org.spring.springboot.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yinxiaoen on 2018/4/13.
 */
@Service
public class DocumentUserRelationServiceImpl implements DocumentUserRelationService {
    @Autowired
    private DocumentUserRelationDao documentUserRelationDao;
    @Override
    public List<DocumentUserRelationDTO> queryDocumentUserRelation(DocumentUserRelationDTO documentUserRelationDTO) {
        return documentUserRelationDao.queryDocumentUserRelation(documentUserRelationDTO);
    }
    @Override
    public void insertDocumentUserRelation(DocumentUserRelationDTO documentUserRelationDTO) {
        List<DocumentUserRelationDTO> list = documentUserRelationDao.queryDocumentUserRelation(documentUserRelationDTO);
        if(CommonUtils.isEmpty(list)){
            documentUserRelationDao.insertDocumentUserRelation(documentUserRelationDTO);
        }
    }
    @Override
    public void updateDocumentUserRelation(DocumentUserRelationDTO documentUserRelationDTO) {
        documentUserRelationDao.updateDocumentUserRelation(documentUserRelationDTO);
    }

    @Override
    public Integer queryDocumentUserRelationCountsByID(@Param("documentID") Long documentID) {
        return documentUserRelationDao.queryDocumentUserRelationCountsByID(documentID);
    }
}
