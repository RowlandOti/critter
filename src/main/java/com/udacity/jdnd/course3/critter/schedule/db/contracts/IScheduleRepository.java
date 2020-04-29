package com.udacity.jdnd.course3.critter.schedule.db.contracts;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.schedule.db.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> findByEmployeeId(long ownerId);
    List<ScheduleEntity> findByPetIds(long petId);
    List<ScheduleEntity> findByCustomerId(long customerId);
}
