package com.Lesson3.DAO;

import com.Lesson3.Model.Storage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class StorageDAO extends GeneralRepositoryImpl<Storage>{

    public StorageDAO() {
        setClass(Storage.class);
    }

    public List getAllStorages() {
        Query query = entityManager.createQuery("SELECT s FROM Storage s", Storage.class);
        return query.getResultList();
    }
}
