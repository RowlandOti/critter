package com.udacity.jdnd.course3.critter.schedule.db;

import com.udacity.jdnd.course3.critter.schedule.db.contracts.IScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.db.contracts.IScheduleService;
import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.db.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.db.contract.ICustomerRepository;
import com.udacity.jdnd.course3.critter.user.db.contract.IEmployeeRepository;
import com.udacity.jdnd.course3.critter.user.db.contract.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    private IScheduleRepository scheduleRepository;

    @Override
    public List<ScheduleEntity> getSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public ScheduleEntity saveSchedule(ScheduleEntity entity) {
        return scheduleRepository.save(entity);
    }

    @Override
    public Optional<ScheduleEntity> getScheduleById(long scheduleId) {
        return scheduleRepository.findById(scheduleId);
    }

    @Override
    public List<ScheduleEntity> getSchedulesByCustomerId(long customerId) {
        //return scheduleRepository.findByCustomerId(customerId);
        return null;
    }

    @Override
    public List<ScheduleEntity> getSchedulesByEmployeeId(long ownerId) {
        return scheduleRepository.findByEmployeeId(ownerId);
    }

    @Override
    public List<ScheduleEntity> getSchedulesByPetId(long petId) {
        return scheduleRepository.findByPetIds(petId);
    }
}
