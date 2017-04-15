package com.czm.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by mac on 17/4/2.
 */
@ConfigurationProperties(prefix = "girl")
@Getter
@Setter
@Component
public class Girl {
    private String name;
    private Integer age;
    private String nameandage;
}
