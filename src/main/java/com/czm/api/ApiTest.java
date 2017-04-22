package com.czm.api;

import com.czm.bean.Girl;
import com.czm.bean.People;
import com.czm.bean.SqlConfig;
import com.czm.dao.PeopleDao;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    @Autowired
    People people;
    @Autowired
    List<JpaRepository> jpaRepositoryList;//list可通过在JpaRepository的实现类中加入@Order进行排序 map不行
    @Autowired
    ApplicationContext applicationContext;
    @Resource(name = "peoples")
    List<People> peoples;

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
    @ApiOperation(value = "测试bean", notes = "测试")
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public People test4(){
        return people;
    }
    @ApiOperation(value = "注解集合", notes = "测试")
    @RequestMapping(value = "/test5", method = RequestMethod.GET)
    public int test5(){
        System.out.println("appliction获取对象:"+applicationContext.getBean("people").getClass().getName());
        return jpaRepositoryList.size();
    }
    @ApiOperation(value = "获取集合", notes = "测试")
    @RequestMapping(value = "/test6", method = RequestMethod.GET)
    public int test6(){
        for(People people:peoples){
            System.out.println("resource注解获取人名:"+people.getName());
        }
        return jpaRepositoryList.size();
    }
    @ApiOperation(value = "测试事务", notes = "测试事务")
    @RequestMapping(value = "/testTransaction", method = RequestMethod.GET)
    @Transactional
    public boolean testTransaction(){
       for(int i=0;i<6;i++){
           People people=new People("aa"+i);
           if(i==4)throw  new RuntimeException("sss");
           dao.save(people);
       }
       return true;
    }
}
