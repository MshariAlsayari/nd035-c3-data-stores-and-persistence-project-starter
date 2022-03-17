package com.udacity.jdnd.course3.critter.user;


import com.google.common.collect.ImmutableList;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetEntity;
import org.assertj.core.util.Lists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        CustomerEntity entity = new CustomerEntity();
        BeanUtils.copyProperties(customerDTO, entity);
        CustomerEntity entity1 = customerRepo.save(entity);
        BeanUtils.copyProperties(entity1, customerDTO);
        return customerDTO;
    }

    public CustomerDTO getOwnerByPetId(long petId) {
        CustomerDTO returnedValue = new CustomerDTO();
        CustomerEntity entity = customerRepo.getOwnerByPetId(petId);
        BeanUtils.copyProperties(entity, returnedValue);
        returnedValue.setPetIds(entity.getPetEntityList().stream().map(PetEntity::getId).collect(Collectors.toList()));
        return returnedValue;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> returnedValue = new ArrayList<>();
        List<CustomerEntity> entities = ImmutableList.copyOf(customerRepo.findAll());


        ModelMapper modelMapper = new ModelMapper();
        for (CustomerEntity entity : entities) {
            CustomerDTO dto = new CustomerDTO();
            modelMapper.map(entity, dto);
            if (entity.getPetEntityList() != null)
            dto.setPetIds(entity.getPetEntityList().stream().map(PetEntity::getId).collect(Collectors.toList()));
            returnedValue.add(dto);
        }

        return returnedValue;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeDTO, entity);
        if (employeeDTO.getDays() != null)
            entity.setDays(new ArrayList<>(employeeDTO.getDays()));
        if (employeeDTO.getSkills() != null)
            entity.setSkills(new ArrayList<>(employeeDTO.getSkills()));
        EmployeeEntity entity1 = employeeRepo.save(entity);
        BeanUtils.copyProperties(entity1, employeeDTO);
        return employeeDTO;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> returnedValue = new ArrayList<>();
        List<EmployeeEntity> entities = ImmutableList.copyOf(employeeRepo.findAll());

        ModelMapper modelMapper = new ModelMapper();
        if (!CollectionUtils.isEmpty(entities))
            returnedValue = modelMapper.map(entities, new TypeToken<List<EmployeeDTO>>() {
            }.getType());

        return returnedValue;
    }

    public EmployeeDTO getEmployeeById(long employeeId) {
        Optional<EmployeeEntity> entity = employeeRepo.findById(employeeId);
        if (entity.isPresent()) {
            EmployeeDTO returnedValue = new EmployeeDTO();
            BeanUtils.copyProperties(entity, returnedValue);
            return returnedValue;
        } else {
            return null;
        }
    }

    public void setAvailability(List<DayOfWeek> daysAvailable, long employeeId) {
        Optional<EmployeeEntity> entity = employeeRepo.findById(employeeId);
        entity.ifPresent(employeeEntity -> {
            entity.get().setDays(daysAvailable);
            employeeRepo.save(entity.get());
        });
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        List<EmployeeDTO> returnedValue = new ArrayList<>();
        List<EmployeeEntity> entities = ImmutableList.copyOf(employeeRepo.findAll());

        entities = entities.stream()
                .filter(e -> e.getSkills().stream()
                .anyMatch(employeeSkill -> employeeDTO.getSkills().contains(employeeSkill)))
                .filter(e -> e.getDays().contains(employeeDTO.getDayPfWeek())).collect(Collectors.toList());


        ModelMapper modelMapper = new ModelMapper();
        if (!CollectionUtils.isEmpty(entities))
            returnedValue = modelMapper.map(entities, new TypeToken<List<EmployeeDTO>>() {
            }.getType());
        return returnedValue;

    }

}
