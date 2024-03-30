package org.acme.mapper;

import org.acme.dto.CitizenDto;
import org.acme.entity.Citizen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi" ,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CitizenMapper {

    @Mapping(target = "firstName", expression = "java(citizenDto.getFullName().substring(0, citizenDto.getFullName().indexOf(' ')))")
    @Mapping(target = "lastName", expression = "java(citizenDto.getFullName().substring(citizenDto.getFullName().indexOf(' ')))")
    @Mapping(target = "address", expression = "java(citizenDto.getAddress()+\"-\"+citizenDto.getPinCode())")
    @Mapping(target = "id", ignore = true)
    Citizen toDAO(CitizenDto citizenDto);

    @Mapping(target = "fullName", expression = "java(citizen.getFirstName()+' '+citizen.getLastName())")
    @Mapping(target = "address", expression = "java(citizen.getAddress().substring(0,citizen.getAddress().indexOf('-')))")
    @Mapping(target = "pinCode", expression = "java(citizen.getAddress().substring(citizen.getAddress().indexOf('-')))")
    CitizenDto toDTO(Citizen citizen);

    @Mapping(target ="id",ignore = true)
    void merge(@MappingTarget Citizen target, Citizen source);
}
