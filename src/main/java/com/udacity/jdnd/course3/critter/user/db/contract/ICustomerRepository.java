package com.udacity.jdnd.course3.critter.user.db.contract;

import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query("select c from Customer c where c.petId = :petId")
    CustomerEntity findByPetId(long petId);
}
