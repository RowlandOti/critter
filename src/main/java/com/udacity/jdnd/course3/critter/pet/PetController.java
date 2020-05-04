package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.contracts.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        PetEntity entity = petService.savePet(PetMapper.convertDTOToPetEntity(petDTO), petDTO.getOwnerId());
        petDTO.setId(entity.getId());
        return petDTO;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        if (petService.getPetById(petId).isPresent()) {
            return PetMapper.convertEntityToPetDTO(petService.getPetById(petId).get());
        } else {
            return null;
        }
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return PetMapper.convertEntityToPetDTO(petService.getPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return PetMapper.convertEntityToPetDTO(petService.getPetsByOwnerId(ownerId));
    }
}
