package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.contracts.IPetRepository;
import com.udacity.jdnd.course3.critter.pet.contracts.IPetService;
import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.db.contract.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService implements IPetService {

    @Autowired
    private IPetRepository petRepository;

    @Autowired
    private ICustomerRepository customerRepository;

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
        CustomerEntity customer = customerRepository.getOne(ownerId);
        return petRepository.findByCustomer(customer);
    }
}
