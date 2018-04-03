package org.spring.springboot.service.impl;

import org.spring.springboot.dao.ProjectDocumentDao;
import org.spring.springboot.domain.ProjectDocument;
import org.spring.springboot.service.ProjectDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */
@Service
public class ProjectDocumentServiceImpl implements ProjectDocumentService{
    @Autowired
    private ProjectDocumentDao projectDocumentDao;
    @Override
    public List<ProjectDocument> findAllDocumentAndProject(ProjectDocument document) {
       return projectDocumentDao.findAllDocumentAndProject(document);
    }

    @Override
    public void insertDocumentAndProject(ProjectDocument document) {
        projectDocumentDao.insertDocumentAndProject(document);
    }

    @Override
    public void updateDocumentAndProject(ProjectDocument document) {
        projectDocumentDao.updateDocumentAndProject(document);
    }

    @Override
    public void deleteDocumentAndProject(String ids) {
        projectDocumentDao.deleteDocumentAndProject(ids);
    }
}
