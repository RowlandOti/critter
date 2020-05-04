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
    public PetEntity savePet(PetEntity pet, long customerId) {
        if (customerId != 0) {
            return savePetWithCustomer(pet, customerId);
        }
        return petRepository.save(pet);
    }

    public PetEntity savePetWithCustomer(PetEntity pet, long customerId) {
        Optional<CustomerEntity> customerOpt = customerRepository.findById(customerId);

        if (customerOpt.isPresent()) {
            CustomerEntity customer = customerOpt.get();
            pet.setCustomer(customer);
            pet = petRepository.save(pet);

            customer.getPets().add(pet);
            customerRepository.save(customer);

            return pet;
        }
        return null;
    }

    @Override
    public Optional<PetEntity> getPetById(long petId) {
        return petRepository.findById(petId);
    }

    @Override
    public List<PetEntity> getPetsByOwnerId(long ownerId) {
        CustomerEntity customer = customerRepository.getOne(ownerId);
        return customer.getPets();
    }
}
