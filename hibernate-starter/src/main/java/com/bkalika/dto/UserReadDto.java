package com.bkalika.dto;

import com.bkalika.entity.PersonalInfo;
import com.bkalika.entity.Role;

public record UserReadDto(Long id,
                          PersonalInfo personalInfo,
                          String username,
                          Role role,
                          CompanyReadDto companyReadDto) {
}
