package org.spring.springboot.service.impl;

import org.spring.springboot.dao.LawBookDetailDao;
import org.spring.springboot.domain.LawBookDTO;
import org.spring.springboot.service.LawBookDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 */
@Service
public class LawBookDetailServiceImpl implements LawBookDetailService {
    @Autowired
    private LawBookDetailDao lawBookDetailDao;
    @Override
    public List<LawBookDTO> readBookDetail(LawBookDTO lawBookDTO) {
        return lawBookDetailDao.readBookDetail(lawBookDTO);
    }

    @Override
    public void insertLawBookDetail(LawBookDTO lawBookDTO) {
        lawBookDetailDao.insertLawBookDetail(lawBookDTO);
    }

    @Override
    public void updateLawBookDetail(LawBookDTO lawBookDTO) {
        lawBookDetailDao.updateLawBookDetail(lawBookDTO);
    }

    @Override
    public void deleteLawBookDetailByID(LawBookDTO lawBookDTO) {
        lawBookDetailDao.deleteLawBookDetailByID(lawBookDTO);
    }
}
