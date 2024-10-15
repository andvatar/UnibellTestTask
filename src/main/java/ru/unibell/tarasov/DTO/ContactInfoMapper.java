package ru.unibell.tarasov.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.unibell.tarasov.entity.ContactInfo;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "contact", target = "contact")
    ContactInfoDTO contactInfoToContactInfoDTO(ContactInfo contactInfo);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "contact", target = "contact")
    ContactInfo contactInfoDTOToContactInfo(ContactInfoDTO contactInfo);
}
