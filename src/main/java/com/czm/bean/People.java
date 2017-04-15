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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;//用户id;
    private String name;
}
