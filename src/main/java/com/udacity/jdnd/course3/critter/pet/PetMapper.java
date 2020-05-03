package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.UserMapper;
import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.db.EmployeeEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PetMapper {
    public static PetDTO convertEntityToPetDTO(PetEntity entity) {
        if(entity != null){
            PetDTO petDTO = new PetDTO();
            //BeanUtils.copyProperties(petDTO, entity);
            petDTO.setId(entity.getId());
            petDTO.setName(entity.getName());
            petDTO.setCustomer(UserMapper.convertEntityToDTO(entity.getCustomer()));
            petDTO.setType(entity.getType());
            petDTO.setBirthDate(entity.getBirthDate());
            petDTO.setNotes(entity.getNotes());

            return petDTO;
        }

        return null;
    }

    public static List<PetDTO> convertEntityToPetDTO(List<PetEntity> petEntities) {
        List<PetDTO> petDTOs = new ArrayList<>();
        petEntities.forEach(petEntity -> petDTOs.add(convertEntityToPetDTO(petEntity)));
        return petDTOs;
    }

    public static PetEntity convertDTOToPetEntity(PetDTO dto) {
        if(dto != null) {
            PetEntity petEntity = new PetEntity();
            //BeanUtils.copyProperties(petEntity, dto);
            petEntity.setId(dto.getId());
            petEntity.setName(dto.getName());
            petEntity.setCustomer(UserMapper.convertDTOToEntity(dto.getCustomer()));
            petEntity.setType(dto.getType());
            petEntity.setBirthDate(dto.getBirthDate());
            petEntity.setNotes(dto.getNotes());

            return petEntity;
        }

        return   null;
    }

    public static List<PetEntity> convertDTOToPetEntity(List<PetDTO> petDtos) {
        List<PetEntity> petEntities = new ArrayList<>();
        petDtos.forEach(petEntity -> petEntities.add(convertDTOToPetEntity(petEntity)));
        return petEntities;
    }
}
