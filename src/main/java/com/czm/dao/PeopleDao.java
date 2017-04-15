package com.czm.dao;

import com.czm.bean.People;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mac on 17/4/2.
 */
public interface PeopleDao extends JpaRepository<People,Long> {
}
