package ru.unibell.tarasov.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.unibell.tarasov.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "patronymicName", target = "patronymicName")
    ClientDTO clientToClientDTO(Client client);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "patronymicName", target = "patronymicName")
    Client clientDTOToClient(ClientDTO client);
}
