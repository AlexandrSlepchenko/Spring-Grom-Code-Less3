package com.Lesson3.Service.Implementation;

import com.Lesson3.DAO.StorageDAO;
import com.Lesson3.Model.Storage;
import com.Lesson3.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class StorageServiceImpl implements StorageService {
    StorageDAO storageDAO;

    @Autowired
    public StorageServiceImpl(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    public Storage save(Storage storage) {
        return storageDAO.save(storage);
    }

    public Storage update(Storage storage) {
        return storageDAO.update(storage);
    }

    public void deleteById(long id) {
        storageDAO.deleteById(id);
    }

    public Storage findById(long id) {
        return storageDAO.findById(id);
    }
}
