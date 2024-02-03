package com.bkalika.service;

import com.bkalika.dao.UserRepository;
import com.bkalika.dto.UserCreateDto;
import com.bkalika.dto.UserReadDto;
import com.bkalika.mapper.UserCreateMapper;
import com.bkalika.mapper.UserReadMapper;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserReadMapper userReadMapper;

    private final UserCreateMapper userCreateMapper;

    public Long create(UserCreateDto userCreateDto) {
        // TODO validation
        var validationFactory = Validation.buildDefaultValidatorFactory();
        var validator = validationFactory.getValidator();
        var validationResult = validator.validate(userCreateDto);
        if (!validationResult.isEmpty())
            throw new ConstraintViolationException(validationResult);

        var userEntity = userCreateMapper.mapFrom(userCreateDto);
        return userRepository.save(userEntity).getId();
    }

    public boolean delete(Long id) {
        var maybeUser = userRepository.findById(id);
        return maybeUser.isPresent();
    }

    public Optional<UserReadDto> findUserById(Long id) {
        return userRepository.findById(id).map(userReadMapper::mapFrom);
    }
}
