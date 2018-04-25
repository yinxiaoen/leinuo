package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.domain.LawBookDTO;
import org.spring.springboot.service.LawBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 */
@RestController
@CrossOrigin
@RequestMapping("/readLawBook")
public class LawBookController {
    @Autowired
    private LawBookService lawBookService;

    @RequestMapping(value = "/readLawBookByTitle", method = RequestMethod.POST)
    public Object readLawBookByTitle(@RequestBody LawBookDTO lawBookDTO) {
        List<LawBookDTO> list =  lawBookService.readBookTitle(lawBookDTO);
        return new Result("0", list);
    }

    @RequestMapping(value = "/readLawBookByPageNo", method = RequestMethod.POST)
    public Object readLawBookByPageNo(@RequestBody LawBookDTO lawBookDTO) {
        List<LawBookDTO> list =  lawBookService.readBookList(lawBookDTO);
        return new Result("0", list);
    }


}
