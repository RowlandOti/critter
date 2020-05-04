package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.schedule.db.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.db.EmployeeEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleMapper {
    public static ScheduleDTO convertEntityToDTO(ScheduleEntity entity) {
        ScheduleDTO dto = new ScheduleDTO();
        BeanUtils.copyProperties(entity, dto);

        dto.setActivities(new HashSet<>(entity.getActivities()));

        dto.setEmployeeIds(entity.getEmployees().stream().map(EmployeeEntity::getId
        ).collect(Collectors.toList()));

        dto.setPetIds(entity.getPets().stream().map(PetEntity::getId
        ).collect(Collectors.toList()));
        return dto;
    }

    public static List<ScheduleDTO> convertEntityToDTO(List<ScheduleEntity> entities) {
        List<ScheduleDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(convertEntityToDTO(entity)));
        return dtos;
    }

    public static ScheduleEntity convertDTOToEntity(ScheduleDTO dto) {
        ScheduleEntity entity = new ScheduleEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
