package org.spring.springboot.controller;

import org.spring.springboot.common.Result;
import org.spring.springboot.dao.RedisDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.spring.springboot.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yinxiaoen on 07/02/2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/documentComment")
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

    @RequestMapping(value = "/api/getRedisCity", method = RequestMethod.GET)
    public Object getRedisCity(String openID) {
        //   return cityService.findCityByName(cityName);
        String city = redisDao.getValue("city"+openID);
        if(!CommonUtils.isBlank(city)){
            city="济南";
        }
        redisDao.setKey("age","11");
        return new Result("0", city);
    }


    @RequestMapping(value = "/api/setRedisCity", method = RequestMethod.GET)
    public Object setRedisCity(String openID,String city) {
        redisDao.setKey("city"+openID,city);
        return new Result("0", "");
    }





}
