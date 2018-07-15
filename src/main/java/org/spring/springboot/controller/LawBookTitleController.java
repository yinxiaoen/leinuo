package org.spring.springboot.controller;
import org.spring.springboot.common.Result;
import org.spring.springboot.domain.LawBookDTO;
import org.spring.springboot.service.LawBookTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinxiaoen on 2018/6/26.
 */
@RestController
@CrossOrigin
@RequestMapping("/readLawBookTitle")
public class LawBookTitleController {
    @Autowired
    private LawBookTitleService lawBookTitleService;

    @RequestMapping(value = "/readLawBookTitleList", method = RequestMethod.POST)
    public Object readLawBookTitleList(@RequestBody LawBookDTO lawBookDTO) {
        List<LawBookDTO> list =  lawBookTitleService.readBookTitleList(lawBookDTO);
        return new Result("0", list);
    }

    @RequestMapping(value = "/readLawBookByTitleByParent", method = RequestMethod.GET)
    public Object readLawBookByTitle(Long id) {
        LawBookDTO lawBookDTO =new LawBookDTO();
        lawBookDTO.setId(id);
        List<LawBookDTO> list =  lawBookTitleService.readBookTitleByParentID(lawBookDTO);
        return new Result("0", list);
    }

    @RequestMapping(value = "/insertLawBookTitle", method = RequestMethod.POST)
    public Object insertLawBookTitle(@RequestBody LawBookDTO lawBookDTO) {
        lawBookTitleService.insertLawBookTitle(lawBookDTO);
        return new Result("0", "SUCCESS");
    }

    @RequestMapping(value = "/updateLawBookTitle", method = RequestMethod.POST)
    public Object updateLawBookTitle(@RequestBody LawBookDTO lawBookDTO) {
        lawBookTitleService.updateLawBookTitle(lawBookDTO);
        return new Result("0", "SUCCESS");
    }

    @RequestMapping(value = "/deleteLawBookTitle", method = RequestMethod.GET)
    public Object deleteLawBookTitle(String id) {
        lawBookTitleService.deleteLawBookTitle(id);
        return new Result("0", "SUCCESS");
    }

    @RequestMapping(value = "/queryLawBookTitleByID", method = RequestMethod.GET)
    public Object queryLawBookTitleByID(Integer id) {
        LawBookDTO lawBookDTO = lawBookTitleService.queryLawBookByID(id);
        List<LawBookDTO>list =new ArrayList();
        list.add(lawBookDTO);
        return new Result("0", list);
    }
}
