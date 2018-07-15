package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.domain.LawBookDTO;
import org.spring.springboot.service.LawBookDetailService;
import org.spring.springboot.service.LawBookTitleService;
import org.spring.springboot.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 */
@RestController
@CrossOrigin
@RequestMapping("/readLawBookDetail")
public class LawBookDetailController {
    @Autowired
    private LawBookDetailService lawBookDetailService;


    @RequestMapping(value = "/readBookSignByUser", method = RequestMethod.POST)
    public Object readLawBookTitleList(@RequestBody LawBookDTO lawBookDTO) {
        List<LawBookDTO> list =  lawBookDetailService.readBookDetail(lawBookDTO);
        return new Result("0", list);
    }


    @RequestMapping(value = "/insertLawBookSign", method = RequestMethod.POST)
    public Object readLawBookByTitle(@RequestBody LawBookDTO lawBookDTO) {
        LawBookDTO lawBookDTOUser =new LawBookDTO();
        lawBookDTOUser.setUserID(lawBookDTO.getUserID());
        lawBookDTOUser.setLawID(lawBookDTO.getLawID());
        List<LawBookDTO> list =  lawBookDetailService.readBookDetail(lawBookDTOUser);
        if(CommonUtils.isEmpty(list)){
            lawBookDetailService.insertLawBookDetail(lawBookDTO);
        }else{
            lawBookDetailService.updateLawBookDetail(lawBookDTO);
        }
        return new Result("0", "SUCCESS");
    }

    @RequestMapping(value = "/updateLawBookDetail", method = RequestMethod.POST)
    public Object insertLawBookTitle(@RequestBody LawBookDTO lawBookDTO) {
        lawBookDetailService.updateLawBookDetail(lawBookDTO);
        return new Result("0", "SUCCESS");
    }


    @RequestMapping(value = "/deleteLawBookDetail", method = RequestMethod.POST)
    public Object deleteLawBookDetail(@RequestBody LawBookDTO lawBookDTO) {
        lawBookDetailService.deleteLawBookDetailByID(lawBookDTO);
        return new Result("0", "SUCCESS");
    }
}
