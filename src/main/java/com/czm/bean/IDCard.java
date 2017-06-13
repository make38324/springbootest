package com.czm.bean;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by mac on 17/5/6.
 */
@Entity
@Getter
@Setter
public class IDCard {
    @Id
    @GeneratedValue(generator = "pid")
    @GenericGenerator(name = "pid",strategy = "assigned")
    @Column(name = "id")
    private String id;
    private String address;
    public IDCard() {
    }
    public IDCard(String id,String address) {
        this.id=id;
        this.address=address;
    }
}
