package com.udacity.jdnd.course3.critter.pet.contracts;

import com.udacity.jdnd.course3.critter.pet.PetEntity;

import java.util.List;
import java.util.Optional;

public interface IPetService {
    List<PetEntity> getPets();
    PetEntity savePet(PetEntity petEntity);
    Optional<PetEntity> getPetById(long petId);
    List<PetEntity> getPetsByOwnerId(long ownerId);
}
