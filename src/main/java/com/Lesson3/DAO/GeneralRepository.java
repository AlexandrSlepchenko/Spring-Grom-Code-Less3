package com.Lesson3.DAO;

public interface GeneralRepository<T> {

    T save(T t);

    T update(T t);

    void deleteById(long id);

    T findById(long id);

}
