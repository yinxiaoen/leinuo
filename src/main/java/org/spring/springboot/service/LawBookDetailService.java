package org.spring.springboot.service;

import org.spring.springboot.domain.LawBookDTO;

import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 */
public interface LawBookDetailService {
    List<LawBookDTO> readBookDetail(LawBookDTO lawBookDTO);

    void insertLawBookDetail(LawBookDTO lawBookDTO);

    void updateLawBookDetail(LawBookDTO lawBookDTO);

    void deleteLawBookDetailByID(LawBookDTO lawBookDTO);
}
