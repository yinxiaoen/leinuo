package org.spring.springboot.service.impl;

import org.spring.springboot.dao.LawBookDao;
import org.spring.springboot.domain.LawBookDTO;
import org.spring.springboot.service.LawBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 殷效恩 on 2018/4/25.
 */
@Service
public class LawBookServiceImpl implements LawBookService {
    @Autowired
    private LawBookDao lawBookDao;
    @Override
    public List<LawBookDTO> readBookList(LawBookDTO lawBookDTO) {
        return lawBookDao.readBookList(lawBookDTO);
    }

    @Override
    public List<LawBookDTO> readBookTitle(LawBookDTO lawBookDTO) {
        return lawBookDao.readBookTitle(lawBookDTO);
    }

    @Override
    public void insertLawBook(LawBookDTO lawBookDTO) {
        lawBookDao.insertLawBook(lawBookDTO);
    }

    @Override
    public void updateLawBook(LawBookDTO lawBookDTO) {
        lawBookDao.updateLawBook(lawBookDTO);
    }

    @Override
    public List<LawBookDTO> readBookList1(LawBookDTO lawBookDTO) {
        return lawBookDao.readBookList1(lawBookDTO);
    }
}
