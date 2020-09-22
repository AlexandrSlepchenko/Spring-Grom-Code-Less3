package com.Lesson3.Service;

import com.Lesson3.DAO.StorageDAO;
import com.Lesson3.Model.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
public class StorageService {
    StorageDAO storageDAO;

    @Autowired
    public StorageService(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    public Storage save(Storage storage) {
        return storageDAO.save(storage);
    }

    public Storage update(Storage storage) {
        return storageDAO.update(storage);
    }

    public void deleteById(long id) {
        storageDAO.delete(id);
    }

    public Storage findById(long id) {
        return (Storage) storageDAO.findById(id);
    }
}
