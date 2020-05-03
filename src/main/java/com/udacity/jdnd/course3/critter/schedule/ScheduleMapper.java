package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetMapper;
import com.udacity.jdnd.course3.critter.schedule.db.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.UserMapper;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMapper {
    public static ScheduleDTO convertEntityToDTO(ScheduleEntity entity) {
        if(entity != null) {
            ScheduleDTO dto = new ScheduleDTO();
            //BeanUtils.copyProperties(dto, entity);
            dto.setActivities(entity.getActivities());
            dto.setDate(entity.getDate());
            dto.setEmployees(UserMapper.convertEmployeeEntityToDTO(entity.getEmployees()));
            dto.setPets(PetMapper.convertEntityToPetDTO(entity.getPets()));
            return dto;
        }

        return null;
    }

    public static List<ScheduleDTO> convertEntityToDTO(List<ScheduleEntity> entities) {
        List<ScheduleDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(convertEntityToDTO(entity)));
        return dtos;
    }

    public static ScheduleEntity convertDTOToEntity(ScheduleDTO dto) {
        if(dto != null) {
            ScheduleEntity entity = new ScheduleEntity();
            //BeanUtils.copyProperties(entity, dto);
            entity.setActivities(dto.getActivities());
            entity.setDate(dto.getDate());
            entity.setEmployees(UserMapper.convertEmployeeDTOToEntity(dto.getEmployees()));

            return entity;
        }

        return null;
    }
}
