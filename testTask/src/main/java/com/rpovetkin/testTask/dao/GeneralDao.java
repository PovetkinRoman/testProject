package com.rpovetkin.testTask.dao;

import java.util.List;

public interface GeneralDao<T> {
    T findById(long id);
    List<T> findAll();
    void save(T obj);
    void delete(long id);
    List<T> findAll(String valueToSort);
    List<T> findAll(String valueToSort, int pageSize);
}
