package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO){
        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeDTO, entity);
        EmployeeEntity entity1 = employeeRepo.save(entity);
        BeanUtils.copyProperties(entity1, employeeDTO);
        return employeeDTO;
    }

}
