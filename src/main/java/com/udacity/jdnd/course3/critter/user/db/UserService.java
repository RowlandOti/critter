package com.udacity.jdnd.course3.critter.user.db;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.contracts.IPetRepository;
import com.udacity.jdnd.course3.critter.pet.contracts.IPetService;
import com.udacity.jdnd.course3.critter.user.db.contract.ICustomerRepository;
import com.udacity.jdnd.course3.critter.user.db.contract.IEmployeeRepository;
import com.udacity.jdnd.course3.critter.user.db.contract.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<EmployeeEntity> getEmployees() {
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
    public Optional<CustomerEntity> getCustomerById(long petId) {
        return customerRepository.findById(petId);
    }

    @Override
    public CustomerEntity getCustomerByPetId(long petId) {
        return customerRepository.findByPetId(petId);
    }
}
