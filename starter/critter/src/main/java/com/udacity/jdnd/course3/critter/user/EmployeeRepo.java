package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends CrudRepository<EmployeeEntity,Long> {
}
