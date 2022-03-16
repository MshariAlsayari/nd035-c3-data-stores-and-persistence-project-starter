package com.udacity.jdnd.course3.critter.schedule;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepo extends CrudRepository<ScheduleEntity,Long> {
}
