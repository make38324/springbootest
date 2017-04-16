package com.czm.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by mac on 17/4/2.
 */
@Entity
@Getter
@Setter
public class People {
    public People(){}
    public People(String name){
        this.name=name;
    }
    public void init(){
        System.out.println("people init");
    }
    public void destory(){
        System.out.println("people destory");
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;//用户id;
    private String name;
}
