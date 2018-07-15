package org.spring.springboot.service;

import org.spring.springboot.domain.LawBookDTO;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 */
public interface LawBookService {
    List<LawBookDTO> readBookList(LawBookDTO lawBookDTO);

    List<LawBookDTO> readBookTitle(LawBookDTO lawBookDTO);

    void insertLawBook(LawBookDTO lawBookDTO);

    void updateLawBook(LawBookDTO lawBookDTO);

    List<LawBookDTO> readBookList1(LawBookDTO lawBookDTO);



}
