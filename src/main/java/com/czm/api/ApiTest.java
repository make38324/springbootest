package com.czm.api;

import com.czm.bean.Girl;
import com.czm.bean.People;
import com.czm.bean.SqlConfig;
import com.czm.dao.PeopleDao;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mac on 17/4/2.
 */
@RestController
@RequestMapping(value = "/api/test", name = "测试类")
public class ApiTest {
    @Autowired
    Girl girl;
    @Autowired
    PeopleDao dao;
    @Autowired
    SqlConfig sqlConfig;
    @Value("${spring.datasource.url}")
    String database;
    @ApiOperation(value = "测试", notes = "测试")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public People test1(@RequestParam String s) {
       return dao.findOne(1l);
    }
    @ApiOperation(value = "测试2", notes = "测试")
    @RequestMapping(value = "/test2", method = {RequestMethod.GET,RequestMethod.POST})
    public String test2() {
//        girl.setName("小红");
        return girl.getName();
    }
    @ApiOperation(value = "测试3", notes = "测试")
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public String test3() {
//        database="ddd";
        sqlConfig.setUrl("kkkdd");
        return sqlConfig.getUrl();
    }
}
