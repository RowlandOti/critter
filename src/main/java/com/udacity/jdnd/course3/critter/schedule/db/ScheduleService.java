package com.udacity.jdnd.course3.critter.schedule.db;

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

import java.util.List;
import java.util.Optional;

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
    public ScheduleEntity saveSchedule(ScheduleEntity entity) {
        return scheduleRepository.save(entity);
    }

    @Override
    public Optional<ScheduleEntity> getScheduleById(long scheduleId) {
        return scheduleRepository.findById(scheduleId);
    }

    @Override
    public List<ScheduleEntity> getSchedulesByCustomerId(long customerId) {
         CustomerEntity customer = customerRepository.getOne(customerId);
         PetEntity pet = petRepository.findByCustomer(customer).get(0);
        return scheduleRepository.findByPets(pet);
    }

    @Override
    public List<ScheduleEntity> getSchedulesByEmployeeId(long employeeId) {
        EmployeeEntity employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.findByEmployees(employee);
    }

    @Override
    public List<ScheduleEntity> getSchedulesByPetId(long petId) {
        PetEntity pet = petRepository.getOne(petId);
        return scheduleRepository.findByPets(pet);
    }
}
