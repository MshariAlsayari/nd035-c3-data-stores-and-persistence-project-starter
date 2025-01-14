package com.udacity.jdnd.course3.critter.pet;


import com.google.common.collect.ImmutableList;
import com.udacity.jdnd.course3.critter.user.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class PetService {

    @Autowired
    PetRepo petRepo;

    @Autowired
    CustomerRepo customerRepo;

    public PetDTO save(PetDTO petDTO){
        Optional<CustomerEntity> customer = customerRepo.findById(petDTO.getOwnerId());
        if (!customer.isPresent()){
            return null;
        }else {
            PetEntity entity = new PetEntity();
            BeanUtils.copyProperties(petDTO, entity);
            entity.setCustomer(customer.get());
            PetEntity savedPet = petRepo.save(entity);
            if (customer.get().getPetEntityList() == null){
                List<PetEntity> petEntityList = new ArrayList<>();
                petEntityList.add(savedPet);
                customer.get().setPetEntityList(petEntityList);
            }else {
                customer.get().getPetEntityList().add(savedPet);
            }

            BeanUtils.copyProperties(savedPet, petDTO);
            return petDTO;
        }

    }

    public PetDTO getPetById(long petId){

        Optional<PetEntity> pet = petRepo.findById(petId);
       if (pet.isPresent()){
           PetDTO returnedValue = new PetDTO();
           BeanUtils.copyProperties(pet.get(), returnedValue);
           returnedValue.setOwnerId(pet.get().getCustomer().getId());
           return returnedValue;
       }else {
           return null;
       }



    }

    public List<PetDTO> getAll(){
        List<PetDTO> returnedValue = new ArrayList<>();
        List<PetEntity> entities = ImmutableList.copyOf(petRepo.findAll());


        ModelMapper modelMapper = new ModelMapper();
        for (PetEntity entity : entities) {
            PetDTO dto = new PetDTO();
            modelMapper.map(entity, dto);
            dto.setOwnerId(entity.getCustomer().getId());
            returnedValue.add(dto);
        }


        return returnedValue;
    }

    public List<PetDTO> getAllPetsByOwner(long ownerId){
        List<PetDTO> returnedValue = new ArrayList<>();
        List<PetEntity> entities = ImmutableList.copyOf(petRepo.getAllByOwner(ownerId));

        ModelMapper modelMapper = new ModelMapper();
        for (PetEntity entity : entities) {
            PetDTO dto = new PetDTO();
            modelMapper.map(entity, dto);
            dto.setOwnerId(entity.getCustomer().getId());
            returnedValue.add(dto);
        }

        return returnedValue;
    }
}
