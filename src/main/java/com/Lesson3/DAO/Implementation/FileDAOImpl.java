package com.Lesson3.DAO.Implementation;

import com.Lesson3.DAO.FileDAO;
import com.Lesson3.Model.File;
import com.Lesson3.Model.Storage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class FileDAOImpl extends GeneralRepositoryImpl<File> implements FileDAO {

    public FileDAOImpl() {
        setClass(File.class);
    }

    public List getAllFiles() {
        Query query = entityManager.createQuery("SELECT f FROM File f", File.class);
        return query.getResultList();
    }

    public File put(Storage storage, File file) {
        file.setStorage(storage);
        return update(file);
    }

    public void delete(Storage storage, File file) {
        if (file.getStorage() != null && file.getStorage().equals(storage)) {
            deleteById(file.getId());
        }
    }

    public List<File> getAllFilesFromStorage(Storage storage) {
        Query query = entityManager.createQuery("SELECT fi FROM File fi WHERE fi.storage =:storage");
        return query.setParameter("storage", storage).getResultList();
    }
}