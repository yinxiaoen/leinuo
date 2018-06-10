package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.domain.LawBookDTO;
import org.spring.springboot.service.LawBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public Object readLawBookByTitle( LawBookDTO lawBookDTO) {
        List<LawBookDTO> list =  lawBookService.readBookTitle(lawBookDTO);
        return new Result("0", list);
    }


    @RequestMapping(value = "/readLawBookByTitleV2", method = RequestMethod.GET)
    public Object readLawBookByTitle(String title) {
        LawBookDTO lawBookDTO = new LawBookDTO();
        lawBookDTO.setTitle(title);
        List<LawBookDTO> list =  lawBookService.readBookTitle(lawBookDTO);
        return new Result("0", list);
    }

    @RequestMapping(value = "/readLawBookByPageNo", method = RequestMethod.POST)
    public Object readLawBookByPageNo(@RequestBody LawBookDTO lawBookDTO) {
        List<LawBookDTO> list =  lawBookService.readBookList(lawBookDTO);
        return new Result("0", list);
    }

    @RequestMapping(value = "/addLawBookTitle", method = RequestMethod.POST)
    public Object addLawBookTitle(@RequestBody LawBookDTO lawBookDTO) {
        lawBookService.insertLawBook(lawBookDTO);
        return new Result("0", "");
    }


    @RequestMapping(value = "/backAllLawBook", method = RequestMethod.GET)
    public Object backAllLawBook() {
        List<String> alllist =new ArrayList();
        for(int i=1;i<1233;i++){
            String url = "http://39.107.247.32:8081/html/法律法规最终版"+i+"_split.html";
            alllist.add(url);
        }
        return new Result("0", alllist);
    }




}
