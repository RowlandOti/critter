package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.schedule.db.ScheduleEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMapper {
   public static ScheduleDTO convertEntityToDTO(ScheduleEntity entity) {
        ScheduleDTO dto = new ScheduleDTO();
        BeanUtils.copyProperties(dto, entity);
        return dto;
    }

    public static List<ScheduleDTO> convertEntityToDTO(List<ScheduleEntity> entities) {
        List<ScheduleDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(convertEntityToDTO(entity)));
        return dtos;
    }

    public static ScheduleEntity convertDTOToEntity(ScheduleDTO dto) {
        ScheduleEntity entity = new ScheduleEntity();
        BeanUtils.copyProperties(entity, dto);
        return entity;
    }
}
