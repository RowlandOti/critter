package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetMapper;
import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.db.EmployeeEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static CustomerDTO convertEntityToDTO(CustomerEntity entity) {
        if(entity != null) {
            CustomerDTO dto = new CustomerDTO();
            //BeanUtils.copyProperties(dto, entity);
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setNotes(entity.getNotes());
            dto.setPhoneNumber(entity.getPhoneNumber());
            dto.setPets(PetMapper.convertEntityToPetDTO(entity.getPets()));

            return dto;
        }

        return  null;
    }

    public static List<CustomerDTO> convertEntityToDTO(List<CustomerEntity> entities) {
        List<CustomerDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(convertEntityToDTO(entity)));
        return dtos;
    }

    public static CustomerEntity convertDTOToEntity(CustomerDTO dto) {
        if(dto != null) {
            CustomerEntity entity = new CustomerEntity();
            //BeanUtils.copyProperties(entity, dto);
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setNotes(dto.getNotes());
            entity.setPhoneNumber(dto.getPhoneNumber());
            entity.setPets(PetMapper.convertDTOToPetEntity(dto.getPets()));

            return entity;
        }

        return  null;
    }


    public static EmployeeDTO convertEntityToDTO(EmployeeEntity entity) {
        EmployeeDTO dto = new EmployeeDTO();
        //BeanUtils.copyProperties(dto, entity);
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSkills(entity.getSkills());
        dto.setDaysAvailable(entity.getDaysAvailable());

        return dto;
    }

    public static List<EmployeeDTO> convertEmployeeEntityToDTO(List<EmployeeEntity> entities) {
        List<EmployeeDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(convertEntityToDTO(entity)));
        return dtos;
    }

    public static EmployeeEntity convertDTOToEntity(EmployeeDTO dto) {
        EmployeeEntity entity = new EmployeeEntity();
        //BeanUtils.copyProperties(entity, dto);
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSkills(dto.getSkills());
        entity.setDaysAvailable(dto.getDaysAvailable());
        return entity;
    }

    public static List<EmployeeEntity> convertEmployeeDTOToEntity(List<EmployeeDTO> dtos) {
        List<EmployeeEntity> entities = new ArrayList<>();
        dtos.forEach(dto -> entities.add(convertDTOToEntity(dto)));
        return entities;
    }
}
