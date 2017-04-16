package com.czm.bean;

import groovy.transform.Field;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;

/**
 * Created by mac on 17/4/16.
 */
@Entity
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;//用户id;
    String name;
}
