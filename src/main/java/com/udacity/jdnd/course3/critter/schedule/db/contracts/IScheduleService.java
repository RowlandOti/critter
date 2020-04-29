package com.udacity.jdnd.course3.critter.schedule.db.contracts;

import com.udacity.jdnd.course3.critter.schedule.db.ScheduleEntity;

import java.util.List;
import java.util.Optional;

public interface IScheduleService {
    List<ScheduleEntity> getSchedules();
    ScheduleEntity saveSchedule(ScheduleEntity entity);
    Optional<ScheduleEntity> getScheduleById(long scheduleId);
    List<ScheduleEntity> getSchedulesByPetId(long petId);
    List<ScheduleEntity> getSchedulesByEmployeeId(long employeeId);
    List<ScheduleEntity> getSchedulesByCustomerId(long customerId);
}
