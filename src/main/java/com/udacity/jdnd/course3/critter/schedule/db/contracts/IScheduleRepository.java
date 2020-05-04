package com.udacity.jdnd.course3.critter.schedule.db.contracts;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.schedule.db.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.db.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface IScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findByPetsIn(List<PetEntity> pets);
    List<ScheduleEntity> findByEmployeesIn(List<EmployeeEntity> employees);
}
