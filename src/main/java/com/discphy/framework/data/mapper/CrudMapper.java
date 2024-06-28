package com.discphy.framework.data.mapper;

import java.util.Optional;

public interface CrudMapper<T, ID> {

    void save(T entity);

    Optional<T> findById(ID id);

    Iterable<T> findAll();

    long count();

    void deleteById(ID id);

    void delete(T entity);
}
