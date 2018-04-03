package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.ProjectDocument;

import java.util.List;

/**
 * Created by yinxiaoen on 2018/3/22.
 */
public interface ProjectDocumentDao {
   List<ProjectDocument> findAllDocumentAndProject(ProjectDocument document);

   void insertDocumentAndProject(ProjectDocument document);

   void updateDocumentAndProject(ProjectDocument document);

   void deleteDocumentAndProject(@Param("ids") String ids);
}
