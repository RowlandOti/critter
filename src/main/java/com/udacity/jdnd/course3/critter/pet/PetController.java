package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.contracts.IPetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private IPetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return convertEntityToPetDTO(petService.savePet(convertDTOToPetEntity(petDTO)));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        if (petService.getPetById(petId).isPresent()) {
            return convertEntityToPetDTO(petService.getPetById(petId).get());
        } else {
            return null;
        }
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return convertEntityToPetDTO(petService.getPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return convertEntityToPetDTO(petService.getPetsByCustomer(ownerId));
    }

    private static PetDTO convertEntityToPetDTO(PetEntity petEntity) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(petDTO, petEntity);
        return petDTO;
    }

    private static List<PetDTO> convertEntityToPetDTO(List<PetEntity> petEntities) {
        List<PetDTO> petDTOs = new ArrayList<>();
        petEntities.forEach(petEntity -> petDTOs.add(convertEntityToPetDTO(petEntity)));
        return petDTOs;
    }

    private static PetEntity convertDTOToPetEntity(PetDTO petDTO) {
        PetEntity petEntity = new PetEntity();
        BeanUtils.copyProperties(petEntity, petDTO);
        return petEntity;
    }
}
