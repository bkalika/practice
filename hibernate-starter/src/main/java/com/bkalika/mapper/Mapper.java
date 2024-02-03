package com.bkalika.mapper;

public interface Mapper<F, T> {
    T mapFrom(F object);
}
