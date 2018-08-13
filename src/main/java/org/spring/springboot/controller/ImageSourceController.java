package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.domain.ImageServerDTO;
import org.spring.springboot.service.ImageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yinxioaen
 * Date: 2018/4/14
 */
@RestController
@CrossOrigin
@RequestMapping("/imageSource")
public class ImageSourceController {
    @Autowired
    private ImageSourceService imageSourceService;

    @RequestMapping(value = "/queryImageList", method = RequestMethod.POST)
    public Object queryImageList(@RequestBody ImageServerDTO imageServerDTO) {
        List<ImageServerDTO> list =  imageSourceService.queryImageTypeList(imageServerDTO);
        return new Result("0", list);
    }

    @RequestMapping(value = "/insertImage", method = RequestMethod.POST)
    public Object insertImage(@RequestBody ImageServerDTO imageServerDTO) {
         imageSourceService.insertLeiNuoImage(imageServerDTO);
        return new Result("0", "success");
    }


    @RequestMapping(value = "/deleteImage", method = RequestMethod.GET)
    public Object deleteImage( String ids) {
        imageSourceService.deleteLeiNuoImageByIDs(ids);
        return new Result("0", "success");
    }
}