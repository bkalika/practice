package com.bkalika.dto;

import com.bkalika.entity.PersonalInfo;
import com.bkalika.entity.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UserCreateDto(
        @Valid
        PersonalInfo personalInfo,
        @NotNull
        String username,
        Role role,
        Long companyId) {
}
