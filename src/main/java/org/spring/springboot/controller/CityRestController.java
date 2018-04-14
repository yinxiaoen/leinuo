package org.spring.springboot.controller;

import org.spring.springboot.dao.RedisDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;
    @Autowired
    RedisDao redisDao;
    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public void findOneCity(@RequestParam(value = "cityName", required = true) String cityName) {
     //   return cityService.findCityByName(cityName);
        redisDao.setKey("name","forezp");
        redisDao.isHaveKey("forezp");
        redisDao.setKey("age","11");
        System.out.print(redisDao.getValue("name"));
    }

}
