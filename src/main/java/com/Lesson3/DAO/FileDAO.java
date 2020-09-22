package com.Lesson3.DAO;

import com.Lesson3.Model.File;
import com.Lesson3.Model.Storage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class FileDAO extends GeneralRepositoryImpl<File> {

    public FileDAO() {
        setClass(File.class);
    }

    public List getAllItems() {
        Query query = entityManager.createQuery("SELECT f FROM File f", File.class);
        return query.getResultList();
    }

    public File put(Storage storage, File file) {
        file.setStorage(storage);
        return (File) update(file);
    }

    public void delete(Storage storage, File file) {
        if (file.getStorage() != null && file.getStorage().equals(storage)) {
            delete(file.getId());
        }
    }

    public List<File> getAllFilesFromStorage(Storage storage) {
        Query query = entityManager.createQuery("SELECT fi FROM File fi WHERE fi.storage =:storage");
        return query.setParameter("storage", storage).getResultList();
    }
}