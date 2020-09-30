package com.Lesson6.DAO.Implementation;

import com.Lesson6.DAO.GeneralDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class GeneralDAOImpl<T> implements GeneralDAO<T> {

    @PersistenceContext
    public EntityManager entityManager;

    private Class<T> tClass;

    public void setClass(Class<T> tClass) {
        this.tClass = tClass;
    }

    public Class<T> getMyType() {
        return this.tClass;
    }

    @Override
    @Transactional
    public T save(T t) {
        entityManager.persist(t);
        return t;
    }

    @Override
    @Transactional
    public T update(T t) {
        entityManager.merge(t);
        return t;
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        entityManager.remove(findById(id));
    }

    @Override
    @Transactional
    public T findById(long id) {
        return entityManager.find(getMyType(), id);
    }
}
