package ru.unibell.tarasov.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClientDTO(int id,
                        @Size(max=100, message="Last name is too long")
                        @NotBlank(message = "Last name is mandatory")
                        String lastName,
                        @Size(max=100, message="First name is too long")
                        @NotBlank(message = "First name is mandatory")
                        String firstName,
                        @Size(max=100, message="Patronymic is too long")
                        String patronymicName) {
}
