package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.user.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends CrudRepository<CustomerEntity, Long> {

    @Query("select c from CustomerEntity c, PetEntity  p where p.id = :petId ")
    CustomerEntity getOwnerByPetId(long petId);
}
