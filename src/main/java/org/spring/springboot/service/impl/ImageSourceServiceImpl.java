package org.spring.springboot.service.impl;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.dao.ImageSourceDao;
import org.spring.springboot.domain.ImageServerDTO;
import org.spring.springboot.service.ImageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yinxiaoen
 * Date: 2018/4/14
 */
@Service
public class ImageSourceServiceImpl implements ImageSourceService {
    @Autowired
    private ImageSourceDao imageSourceDao;
    @Override
    public List<ImageServerDTO> queryImageTypeList( ImageServerDTO imageServerDTO) {
        List<ImageServerDTO> list =  imageSourceDao.queryImageTypeList(imageServerDTO);
        return list;
    }

    @Override
    public void insertLeiNuoImage(ImageServerDTO imageServerDTO) {
        imageSourceDao.insertLeiNuoImage(imageServerDTO);
    }

    @Override
    public void deleteLeiNuoImageByIDs(@Param("ids") String ids) {
        imageSourceDao.deleteLeiNuoImageByIDs(ids);
    }
}