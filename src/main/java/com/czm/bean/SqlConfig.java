package com.czm.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by mac on 17/4/2.
 */
@ConfigurationProperties(prefix = "spring.datasource")
@Component
@Getter
@Setter
public class SqlConfig {
    private String url;
}
