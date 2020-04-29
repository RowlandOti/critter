package com.udacity.jdnd.course3.critter.user.db.contract;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByPets(PetEntity pet);
}
