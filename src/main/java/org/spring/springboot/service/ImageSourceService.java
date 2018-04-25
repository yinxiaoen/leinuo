package org.spring.springboot.service;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.ImageServerDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhoudapeng
 * Date: 2018/4/14
 */
public interface ImageSourceService {
    List<ImageServerDTO> queryImageTypeList(ImageServerDTO imageServerDTO);
}