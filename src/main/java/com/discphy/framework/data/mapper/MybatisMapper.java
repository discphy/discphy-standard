package com.discphy.framework.data.mapper;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface MybatisMapper<T, ID> extends PagingAndSortingMapper<T, ID> {

    List<T> findAll();

    List<T> findAll(Sort sort);

}
