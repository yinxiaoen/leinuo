package org.spring.springboot.service;

import org.spring.springboot.domain.ProjectDocument;

import java.util.List;

/**
 * Created by yinxiaoen on 2018/3/22.
 */
public interface ProjectDocumentService {
    List<ProjectDocument> findAllDocumentAndProject(ProjectDocument document);

    void insertDocumentAndProject(ProjectDocument document);

    void updateDocumentAndProject(ProjectDocument document);


    void deleteDocumentAndProject( String ids,String htmlPath);

    ProjectDocument  queryDocmentByID(Integer id);

    void deleteDocumentAndProjectV2(String ids);
}
