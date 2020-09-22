package com.Lesson3.DAO;

import com.Lesson3.Model.Storage;
import com.Lesson6.DAO.Impl.GeneralDAOImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class StorageDAO extends GeneralDAOImpl {
    public StorageDAO() {
        setClass(Storage.class);
    }

    public Storage save(Storage storage) {
        entityManager.persist(storage);
        return storage;
    }

    public Storage update(Storage storage) {
        entityManager.merge(storage);
        return storage;
    }

    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    public Storage findById(Long id) {
        return entityManager.find(Storage.class, id);
    }

    public List getAllStorages() {
        Query query = entityManager.createQuery("SELECT s FROM Storage s", Storage.class);
        return query.getResultList();
    }
}
