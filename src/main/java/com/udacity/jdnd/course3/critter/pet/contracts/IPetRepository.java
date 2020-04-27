package com.udacity.jdnd.course3.critter.pet.contracts;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPetRepository extends JpaRepository<PetEntity, Long> {
    @Query("select p from Pet p where p.ownerId = :ownerId")
    List<PetEntity> findByOwnerId(long ownerId);
}
