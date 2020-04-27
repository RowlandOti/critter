package com.udacity.jdnd.course3.critter.user.db.contract;

import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.db.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<EmployeeEntity> getEmployees();
    EmployeeEntity saveEmployee(EmployeeEntity entity);
    Optional<EmployeeEntity> getEmployeeById(long id);

    List<CustomerEntity> getCustomers();
    CustomerEntity saveCustomer(CustomerEntity entity);
    Optional<CustomerEntity> getCustomerById(long id);

    CustomerEntity getCustomerByPetId(long petId);
}
