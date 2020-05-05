package com.udacity.jdnd.course3.critter.pet.contracts;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPetRepository extends JpaRepository<PetEntity, Long> {
    List<PetEntity> findByCustomer(CustomerEntity customer);
}
