package com.czm;

import com.czm.bean.People;
import com.czm.bean.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 17/4/16.
 */
@Configuration
public class BeanConfig {
    @Bean(name = "people",initMethod = "init",destroyMethod = "destory")
    public People getPeople(){
        return new People("张三");
    }
    @Bean(name = "peoples")
    public List<People> peopleList(){
        List<People> peoples=new ArrayList<People>();
        peoples.add(getPeople());
        peoples.add(new People("李四"));
        return peoples;
    }
}
