package com.udacity.jdnd.course3.critter.schedule.db.contracts;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IScheduleRepository extends JpaRepository<PetEntity, Long> {

}
