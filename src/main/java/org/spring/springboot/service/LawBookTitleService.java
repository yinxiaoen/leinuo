package org.spring.springboot.service;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.LawBookDTO;

import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 */
public interface LawBookTitleService {
    List<LawBookDTO> readBookTitleList(LawBookDTO lawBookDTO);

    List<LawBookDTO> readBookTitleByParentID(LawBookDTO lawBookDTO);

    void insertLawBookTitle(LawBookDTO lawBookDTO);

    void updateLawBookTitle(LawBookDTO lawBookDTO);

    void deleteLawBookTitle(@Param("ids") String ids);

    LawBookDTO queryLawBookByID(Integer id);
}
