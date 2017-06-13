package com.czm.dao;

import com.czm.bean.IDCard;
import com.czm.bean.People;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mac on 17/5/6.
 */
public interface IDCardDao extends JpaRepository<IDCard,Long> {
}
