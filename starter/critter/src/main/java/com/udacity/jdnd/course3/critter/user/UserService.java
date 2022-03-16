package com.udacity.jdnd.course3.critter.user;


import com.google.common.collect.ImmutableList;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    public CustomerDTO saveCustomer(CustomerDTO customerDTO){
        CustomerEntity entity = new CustomerEntity();
        BeanUtils.copyProperties(customerDTO, entity);
        CustomerEntity entity1 = customerRepo.save(entity);
        BeanUtils.copyProperties(entity1, customerDTO);
        return customerDTO;
    }

    public CustomerDTO getOwnerByPetId(long petId){
        CustomerDTO returnedValue = new CustomerDTO();
        CustomerEntity entity = customerRepo.getOwnerByPetId(petId);
        BeanUtils.copyProperties(entity, returnedValue);
        return returnedValue;
    }

    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> returnedValue = new ArrayList<>();
        List<CustomerEntity> entities = ImmutableList.copyOf(customerRepo.findAll());


        ModelMapper modelMapper = new ModelMapper();
        for (CustomerEntity entity : entities) {
            CustomerDTO dto = new CustomerDTO();
            modelMapper.map(entity, dto);
            dto.setPetIds(entity.getPetEntityList().stream().map(PetEntity::getId).collect(Collectors.toList()));
            returnedValue.add(dto);
        }

        return returnedValue;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO){
        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeDTO, entity);
        EmployeeEntity entity1 = employeeRepo.save(entity);
        BeanUtils.copyProperties(entity1, employeeDTO);
        return employeeDTO;
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeDTO> returnedValue = new ArrayList<>();
        List<EmployeeEntity> entities = ImmutableList.copyOf(employeeRepo.findAll());

        ModelMapper modelMapper = new ModelMapper();
        if (!CollectionUtils.isEmpty(entities))
            returnedValue = modelMapper.map(entities, new TypeToken<List<EmployeeDTO>>() {
            }.getType());

        return returnedValue;
    }

}
