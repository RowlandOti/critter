package com.udacity.jdnd.course3.critter.user.db.contract;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByPetsIn(List<PetEntity> pets);
}
