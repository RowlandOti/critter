package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.contracts.IPetRepository;
import com.udacity.jdnd.course3.critter.pet.contracts.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService implements IPetService {

    @Autowired
    private IPetRepository petRepository;

    @Override
    public List<PetEntity> getPets() {
        return petRepository.findAll();
    }

    @Override
    public PetEntity savePet(PetEntity petEntity) {
        return petRepository.save(petEntity);
    }

    @Override
    public Optional<PetEntity> getPetById(long petId) {
        return petRepository.findById(petId);
    }

    @Override
    public List<PetEntity> getPetsByOwnerId(long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }
}
