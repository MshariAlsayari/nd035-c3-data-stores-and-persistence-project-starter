package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.user.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends CrudRepository<CustomerEntity, Long> {
}
