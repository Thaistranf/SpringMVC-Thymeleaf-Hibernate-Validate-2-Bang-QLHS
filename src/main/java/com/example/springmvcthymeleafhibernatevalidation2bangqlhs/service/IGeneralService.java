package com.example.springmvcthymeleafhibernatevalidation2bangqlhs.service;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    void save(T t);

    Optional<T> findById(Long id);

    void remove(Long id);
}
