package com.czm.api;

import com.czm.bean.IDCard;
import com.czm.bean.People;
import com.czm.dao.IDCardDao;
import com.czm.dao.PeopleDao;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by mac on 17/5/6.
 */
@RestController
@RequestMapping(value = "/api/o2o", name = "one2one测试")
public class One2OneTest {
    @Autowired
    IDCardDao cardDao;
    @Autowired
    PeopleDao peopleDao;

    @ApiOperation(value = "测试添加",notes = "添加")
    @GetMapping(value = "/add")
    public void addIDandPeople(){
        IDCard idCard=new IDCard(UUID.randomUUID().toString(),"aaabbb");
        People people=new People("张三");
        people.setCard(idCard);
        cardDao.save(idCard);
        peopleDao.save(people);
    }
    @ApiOperation(value = "查询身份证",notes = "查询")
    @GetMapping(value = "/find")
    public IDCard find(@RequestParam long pid){
        return peopleDao.findOne(pid).getCard();
    }
}
