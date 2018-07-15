package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.spring.springboot.domain.LawBookDTO;

import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 */
@Mapper
public interface LawBookDetailDao {

    List<LawBookDTO> readBookDetail(LawBookDTO lawBookDTO);

    void insertLawBookDetail(LawBookDTO lawBookDTO);

    void updateLawBookDetail(LawBookDTO lawBookDTO);

    void deleteLawBookDetailByID(LawBookDTO lawBookDTO);


}
