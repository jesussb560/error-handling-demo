package com.jesussb.demo.error_handling.user.dto.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Create(
        @NotBlank String name,
        @NotBlank String lastname,
        @Email String email,
        @NotNull @Min(value = 18) int age
) {
}
