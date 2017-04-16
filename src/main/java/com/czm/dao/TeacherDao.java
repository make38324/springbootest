package com.czm.dao;

import com.czm.bean.People;
import com.czm.bean.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mac on 17/4/2.
 */
public interface TeacherDao extends JpaRepository<Teacher,Long> {
}
