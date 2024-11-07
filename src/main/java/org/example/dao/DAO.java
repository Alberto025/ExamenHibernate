package org.example.dao;

import java.util.List;

public interface DAO<T> {
    List<T> findAll();
    T findByID(Long id);
    void insert(T t);
    void delete(T t);
    void update(T t);

}
