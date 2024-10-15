package ru.unibell.tarasov.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ru.unibell.tarasov.entity.ContactType;

public record ContactInfoDTO(int id,
                             ContactType type,
                             @NotBlank(message="Contact is mandatory")
                             @Size(max = 100, message = "Contact is too long")
                             String contact) {
}
