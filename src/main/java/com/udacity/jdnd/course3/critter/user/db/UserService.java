package com.udacity.jdnd.course3.critter.user.db;

import com.google.common.collect.Lists;
import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.contracts.IPetRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.db.contract.ICustomerRepository;
import com.udacity.jdnd.course3.critter.user.db.contract.IEmployeeRepository;
import com.udacity.jdnd.course3.critter.user.db.contract.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private IPetRepository petRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<EmployeeEntity> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeEntity saveEmployee(EmployeeEntity entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public Optional<EmployeeEntity> getEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<CustomerEntity> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity entity) {
        return customerRepository.save(entity);
    }

    @Override
    public Optional<CustomerEntity> findById(long petId) {
        return customerRepository.findById(petId);
    }

    @Override
    public CustomerEntity getCustomerByPetId(long petId) {
        PetEntity pet = petRepository.getOne(petId);
        return customerRepository.findByPetsIn(Lists.newArrayList(pet));
    }

    @Override
    public List<EmployeeEntity> getEmployeeForService(Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        List<EmployeeEntity> employeesSuspected = employeeRepository.findAllBySkillsInAndDaysAvailableIn(skills, daysAvailable);

        List<EmployeeEntity> employeesForService = new ArrayList<>();

        employeesSuspected.forEach(employee -> {
            Set<EmployeeSkill> intersectionSkills = new HashSet<>(skills);
            intersectionSkills.retainAll(employee.getSkills());

            // Does employee possess required set of skills
            if((intersectionSkills.size() == skills.size())) {
                employeesForService.add(employee);
            }
        });

        return employeesForService;
    }
}
