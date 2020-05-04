package com.udacity.jdnd.course3.critter.schedule.db;

import com.google.common.collect.Lists;
import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.contracts.IPetRepository;
import com.udacity.jdnd.course3.critter.schedule.db.contracts.IScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.db.contracts.IScheduleService;
import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.db.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.db.contract.ICustomerRepository;
import com.udacity.jdnd.course3.critter.user.db.contract.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    private IScheduleRepository scheduleRepository;

    @Autowired
    private IPetRepository petRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private ICustomerRepository customerRepository;


    @Override
    public List<ScheduleEntity> getSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public ScheduleEntity saveSchedule(ScheduleEntity entity, List<Long> employeeIds, List<Long> petIds) {

        employeeIds.forEach(employeeId -> {
            EmployeeEntity employee = employeeRepository.getOne(employeeId);
            entity.getEmployees().add(employee);
        });

        petIds.forEach(petId -> {
            PetEntity pet = petRepository.getOne(petId);
            entity.getPets().add(pet);
        });

        ScheduleEntity schedule =  scheduleRepository.save(entity);

        entity.getEmployees().forEach(employee -> {
            employee.getSchedules().add(schedule);
            employeeRepository.save(employee);
        });

        entity.getPets().forEach(pet -> {
            pet.getSchedules().add(schedule);
            petRepository.save(pet);
        });

        return schedule;
    }

    @Override
    public Optional<ScheduleEntity> getScheduleById(long scheduleId) {
        return scheduleRepository.findById(scheduleId);
    }

    @Override
    public List<ScheduleEntity> getSchedulesByCustomerId(long customerId) {
        Optional<CustomerEntity> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            List<PetEntity> pets = petRepository.findByCustomer(customerOpt.get());
            return scheduleRepository.findByPetsIn(pets);
        }
        return Collections.emptyList();
    }

    @Override
    public List<ScheduleEntity> getSchedulesByEmployeeId(long employeeId) {
        Optional<EmployeeEntity> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isPresent()) {
            return scheduleRepository.findByEmployeesIn(Lists.newArrayList(employeeOpt.get()));
        }
        return Collections.emptyList();
    }

    @Override
    public List<ScheduleEntity> getSchedulesByPetId(long petId) {
        Optional<PetEntity> petOpt = petRepository.findById(petId);
        if (petOpt.isPresent()) {
            return scheduleRepository.findByPetsIn(Lists.newArrayList(petOpt.get()));
        }
        return Collections.emptyList();
    }
}
