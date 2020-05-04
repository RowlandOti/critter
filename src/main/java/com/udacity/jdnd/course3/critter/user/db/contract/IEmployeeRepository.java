package com.udacity.jdnd.course3.critter.user.db.contract;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.db.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findAllBySkillsInAndDaysAvailableIn(Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable);
}
