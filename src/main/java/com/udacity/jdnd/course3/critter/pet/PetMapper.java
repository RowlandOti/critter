package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PetMapper {
    public static List<PetDTO> convertEntityToPetDTO(List<PetEntity> petEntities) {
        List<PetDTO> petDTOs = new ArrayList<>();
        petEntities.forEach(petEntity -> petDTOs.add(convertEntityToPetDTO(petEntity)));
        return petDTOs;
    }

    public static PetDTO convertEntityToPetDTO(PetEntity petEntity) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(petEntity, petDTO);

        // As pets can't be added without owners, it is sure owned
        petDTO.setOwnerId(petEntity.getCustomer().getId());
        return petDTO;
    }

    public static PetEntity convertDTOToPetEntity(PetDTO petDTO) {
        PetEntity petEntity = new PetEntity();
        BeanUtils.copyProperties(petDTO, petEntity);
        return petEntity;
    }
}
