package com.Lesson3.DAO;

import com.Lesson3.Model.File;
import com.Lesson3.Model.Storage;

import java.util.List;

public interface FileDAO {

    File save(File file);
    File update(File file);
    void deleteById(long id);
    File findById(long id);

    List getAllFiles();
    File put(Storage storage, File file);
    void delete(Storage storage, File file);
    List<File> getAllFilesFromStorage(Storage storage);
}
