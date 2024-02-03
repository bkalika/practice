package com.bkalika.mapper;

import com.bkalika.dto.CompanyReadDto;
import com.bkalika.entity.Company;

public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {
    @Override
    public CompanyReadDto mapFrom(Company object) {
        return new CompanyReadDto(object.getId(),
                object.getName());
    }
}
