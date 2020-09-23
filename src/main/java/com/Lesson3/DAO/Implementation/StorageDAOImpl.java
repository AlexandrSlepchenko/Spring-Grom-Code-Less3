package com.Lesson3.DAO.Implementation;

import com.Lesson3.DAO.StorageDAO;
import com.Lesson3.Model.Storage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class StorageDAOImpl extends GeneralRepositoryImpl<Storage> implements StorageDAO {

    public StorageDAOImpl() {
        setClass(Storage.class);
    }

    public List getAllStorages() {
        Query query = entityManager.createQuery("SELECT s FROM Storage s", Storage.class);
        return query.getResultList();
    }
}
