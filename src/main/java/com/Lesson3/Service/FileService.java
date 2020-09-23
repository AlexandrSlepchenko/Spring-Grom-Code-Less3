package com.Lesson3.Service;

import com.Lesson3.Model.File;
import com.Lesson3.Model.Storage;

import java.util.List;

public interface FileService {

    File save(File file);
    File update(File file);
    void deleteById(long id);
    File findById(long id);


    File put(Storage storage, File file);
    void delete(Storage storage, File file);
    List transferAll(Storage storageFrom, Storage storageTo);
    File transferFile(Storage storage, Long id);

}
