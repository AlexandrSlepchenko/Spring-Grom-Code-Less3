package com.Lesson6.DAO;

public interface GeneralDAO<T> {

    T save(T t);
    T update(T t);
    void deleteById(long id);
    T findById(long id);

}
