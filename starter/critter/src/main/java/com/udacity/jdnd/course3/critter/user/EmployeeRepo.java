package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EmployeeRepo extends CrudRepository<EmployeeEntity,Long> {

    @Query("select e from EmployeeEntity e where :day in e.days")
    List<EmployeeEntity> findEmployeesForService(DayOfWeek day);
}
