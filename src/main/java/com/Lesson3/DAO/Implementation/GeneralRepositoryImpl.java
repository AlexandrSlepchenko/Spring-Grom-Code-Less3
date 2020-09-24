package com.Lesson3.DAO.Implementation;

import com.Lesson3.DAO.GeneralRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class GeneralRepositoryImpl<T> implements GeneralRepository<T> {

    private Class tClass;

    public void setClass(Class tClass) {
        this.tClass = tClass;
    }

    public Class<T> getMyType() {
        return this.tClass;
    }

    @PersistenceContext
    public EntityManager entityManager;

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
        entityManager.remove(entityManager.find(getMyType(), id));
    }

    @Override
    @Transactional
    public T findById(long id) {
        return entityManager.find(getMyType(), id);
    }
}
