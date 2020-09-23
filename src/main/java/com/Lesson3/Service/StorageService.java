package com.Lesson3.Service;

import com.Lesson3.Model.Storage;

public interface StorageService {

    Storage save(Storage storage);

    Storage update(Storage storage);

    void deleteById(long id);

    Storage findById(long id);
}
