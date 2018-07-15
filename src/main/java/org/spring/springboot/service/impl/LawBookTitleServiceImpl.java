package org.spring.springboot.service.impl;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.dao.LawBookTitleDao;
import org.spring.springboot.domain.LawBookDTO;
import org.spring.springboot.service.LawBookTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yinxiaoen on 2018/6/26.
 */
@Service
public class LawBookTitleServiceImpl implements LawBookTitleService {
    @Autowired
    private LawBookTitleDao lawBookTitleDao;

    @Override
    public List<LawBookDTO> readBookTitleList(LawBookDTO lawBookDTO) {
        return lawBookTitleDao.readBookTitleList(lawBookDTO);
    }

    @Override
    public List<LawBookDTO> readBookTitleByParentID(LawBookDTO lawBookDTO) {
        return lawBookTitleDao.readBookTitleByParentID(lawBookDTO);
    }

    @Override
    public void insertLawBookTitle(LawBookDTO lawBookDTO) {
        lawBookTitleDao.insertLawBookTitle(lawBookDTO);

    }

    @Override
    public void updateLawBookTitle(LawBookDTO lawBookDTO) {
        lawBookTitleDao.updateLawBookTitle(lawBookDTO);
    }

    @Override
    public void deleteLawBookTitle(@Param("ids") String ids) {
        lawBookTitleDao.deleteLawBookTitle(ids);
        lawBookTitleDao.deleteLawBookTitleByParent(ids);
    }

    @Override
    public LawBookDTO queryLawBookByID(Integer id) {
        return lawBookTitleDao.queryLawBookByID(id);
    }
}
