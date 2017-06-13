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
public class Cardteacher {
    @Id
    @GeneratedValue(generator = "pid")
    @GenericGenerator(name = "pid",strategy = "assigned")
    @Column(name = "id")
    private String id;
    @Embedded
    Address address;
    @OneToOne(mappedBy = "cardteacher")
    private Teacher teacher;

    public Cardteacher() {
    }
    public Cardteacher(String id,String city,String address) {
        this.id=id;
        this.address.setCity(city);
        this.address.setSummary(address);
    }
}
