package com.udacity.jdnd.course3.critter.schedule;


import com.google.common.collect.ImmutableList;
import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetRepo;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeRepo;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    PetRepo petRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ScheduleRepo scheduleRepo;

    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO){
        List<PetEntity> pets = Lists.newArrayList(petRepo.findAllById(scheduleDTO.getPetIds()));
        List<EmployeeEntity> employees = Lists.newArrayList(employeeRepo.findAllById(scheduleDTO.getEmployeeIds()));
        boolean isValidPets = !Lists.newArrayList(pets).isEmpty();
        boolean isValidEmployees = !Lists.newArrayList(employees).isEmpty();

        if (isValidEmployees && isValidPets){
            ScheduleEntity savedEntity = new ScheduleEntity();
            savedEntity.setDate(scheduleDTO.getDate());
            savedEntity.setSkills(Lists.newArrayList(scheduleDTO.getSkills()));
            savedEntity.setPetEntityList(pets);
            savedEntity.setEmployeeEntityList(employees);

            ScheduleEntity entity = scheduleRepo.save(savedEntity);

            ScheduleDTO returnedValue = new ScheduleDTO();
            returnedValue.setId(entity.getId());
            returnedValue.setDate(entity.getDate());
            returnedValue.setSkills(new HashSet<>(entity.getSkills()));
            returnedValue.setEmployeeIds(entity.getEmployeeEntityList().stream().map(EmployeeEntity::getId).collect(Collectors.toList()));
            returnedValue.setPetIds(entity.getPetEntityList().stream().map(PetEntity::getId).collect(Collectors.toList()));
            return  returnedValue;
        }else {
            return null;
        }

    }

    public List<ScheduleDTO> getAllSchedule(){
        List<ScheduleDTO> returnedValue = new ArrayList<>();
        List<ScheduleEntity> entities = ImmutableList.copyOf(scheduleRepo.findAll());

        for (ScheduleEntity entity : entities) {
            ScheduleDTO dto = new ScheduleDTO();
            dto.setId(entity.getId());
            dto.setDate(entity.getDate());
            dto.setSkills(new HashSet<>(entity.getSkills()));
            dto.setEmployeeIds(entity.getEmployeeEntityList().stream().map(EmployeeEntity::getId).collect(Collectors.toList()));
            dto.setPetIds(entity.getPetEntityList().stream().map(PetEntity::getId).collect(Collectors.toList()));
            returnedValue.add(dto);
        }
        return returnedValue;
    }


}
