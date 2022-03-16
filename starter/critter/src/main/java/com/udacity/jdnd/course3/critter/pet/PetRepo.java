package com.udacity.jdnd.course3.critter.pet;


import com.udacity.jdnd.course3.critter.pet.PetEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepo  extends CrudRepository<PetEntity, Long> {

    @Query("select p from PetEntity p where p.customer.id =:ownerId ")
    List<PetEntity> getAllByOwner(long ownerId);
}
