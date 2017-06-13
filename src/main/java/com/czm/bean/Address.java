package com.czm.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by mac on 17/5/6.
 */
@Getter
@Setter
@Embeddable
public class Address {
    private String city;//市
    private String summary;//详细地址
}
