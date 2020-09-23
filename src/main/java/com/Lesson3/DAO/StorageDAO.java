package com.Lesson3.DAO;

import com.Lesson3.Model.Storage;

import java.util.List;

public interface StorageDAO {

    Storage save(Storage storage);
    Storage update(Storage storage);
    void deleteById(long id);
    Storage findById(long id);

    List getAllStorages();
}
