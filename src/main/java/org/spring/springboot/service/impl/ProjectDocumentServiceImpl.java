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
        printProjectDocument(document);
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




    private void printProjectDocument(ProjectDocument document){
        switch (document.getCategoryID()){
            case 1:
                document.setCategoryName("企业产股权");
                break;
            case 2:
                document.setCategoryName("企业增资扩股");
                break;
            case 3:
                document.setCategoryName("股权");
                break;
            case 4:
                document.setCategoryName("增资");
                break;
            case 5:
                document.setCategoryName("实物资产");
                break;
            case 6:
                document.setCategoryName("网络竞价");
                break;

            default:
                break;
        }
        switch (document.getProjectID()){
            case 1:
                document.setProjectName("山东产权交易所");
                break;
            case 2:
                document.setProjectName("北京产权交易所");
                break;
            case 3:
                document.setProjectName("上海产权交易所");
                break;
            default:
                break;
        }
    }
}
