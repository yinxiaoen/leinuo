package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.spring.springboot.domain.LawBookDTO;

import java.util.List;

/**
 * Created by yinxiaoen on 2018/4/25.
 */
@Mapper
public interface LawBookDao {

    List<LawBookDTO> readBookList(LawBookDTO lawBookDTO);

    List<LawBookDTO> readBookTitle(LawBookDTO lawBookDTO);

    void insertLawBook(LawBookDTO lawBookDTO);
}
