package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.LawBookDTO;

import java.util.List;

/**
 * Created by yinxiaoen on 2018/6/26.
 */
@Mapper
public interface LawBookTitleDao {
    List<LawBookDTO> readBookTitleList(LawBookDTO lawBookDTO);

    List<LawBookDTO> readBookTitleByParentID(LawBookDTO lawBookDTO);

    void insertLawBookTitle(LawBookDTO lawBookDTO);

    void updateLawBookTitle(LawBookDTO lawBookDTO);

    void deleteLawBookTitle(@Param("ids") String ids);

    void deleteLawBookTitleByParent(@Param("ids") String ids);

    LawBookDTO queryLawBookByID(Integer id);
}
