package com.richcode.search.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface SearchRepository<T, ID extends Serializable>  extends PagingAndSortingRepository<T, ID>, CrudRepository<T, ID> {

    List<T> find(String text, int limit, String... fields);
}