package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.db.EmployeeEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
   public static CustomerDTO convertEntityToDTO(CustomerEntity entity) {
        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(dto, entity);
        return dto;
    }

    public static List<CustomerDTO> convertEntityToDTO(List<CustomerEntity> entities) {
        List<CustomerDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(convertEntityToDTO(entity)));
        return dtos;
    }

    public static CustomerEntity convertDTOToEntity(CustomerDTO dto) {
        CustomerEntity entity = new CustomerEntity();
        BeanUtils.copyProperties(entity, dto);
        return entity;
    }




    public static EmployeeDTO convertEntityToDTO(EmployeeEntity entity) {
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(dto, entity);
        return dto;
    }

    public static List<EmployeeDTO> convertEmployeeEntityToDTO(List<EmployeeEntity> entities) {
        List<EmployeeDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(convertEntityToDTO(entity)));
        return dtos;
    }

    public static EmployeeEntity convertDTOToEntity(EmployeeDTO dto) {
        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(entity, dto);
        return entity;
    }
}
