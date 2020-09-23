package com.Lesson3.Service.Implementation;


import com.Lesson3.DAO.FileDAO;
import com.Lesson3.Model.File;
import com.Lesson3.Model.Storage;
import com.Lesson3.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class FileServiceImpl implements FileService {

    FileDAO fileDAO;

    @Autowired
    public FileServiceImpl(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public File save(File file) {
        return fileDAO.save(file);
    }

    public File update(File file) {
        return fileDAO.update(file);
    }

    public void deleteById(long id) {
        fileDAO.deleteById(id);
    }

    public File findById(long id) {
        return fileDAO.findById(id);
    }

    public File put(Storage storage, File file) {
        if (validateSize(storage, file)) {
            file.setStorage(storage);
            return fileDAO.save(file);
        }
        return null;
    }

    public void delete(Storage storage, File file) {
        if (file.getStorage() != null && file.getStorage().equals(storage)) {
            fileDAO.deleteById(file.getId());
        }
    }

    public List transferAll(Storage storageFrom, Storage storageTo) {
        List<File> files = fileDAO.getAllFilesFromStorage(storageFrom);
        if (validateSizeStorage(storageFrom, storageTo)) {
            for (File file : files) {
                validateFormat(storageTo, file);
                put(storageTo, file);
            }
        }
        return fileDAO.getAllFilesFromStorage(storageTo);
    }

    public File transferFile(Storage storageTo, Long id) {
        return put(storageTo, findById(id));
    }

    private boolean validateSize(Storage storage, File file) {
        return getFreeSpace(storage) > file.getSize();
    }

    private boolean validateFormat(Storage storage, File file) {
        for (String format : storage.getFormatsSupported().split(",")) {
            if (file.getFormat().equals(format)) {
                return true;
            }
        }
        return false;
    }

    private boolean validateSizeStorage(Storage storageFrom, Storage storageTo) {
        return getFreeSpace(storageTo) > getOccupiedSpace(storageFrom);
    }

    private long getFreeSpace(Storage storage) {
        return storage.getStorageMaxSize() - getOccupiedSpace(storage);
    }

    private long getOccupiedSpace(Storage storage) {
        long occupiedSpace = 0;
        for (File file : fileDAO.getAllFilesFromStorage(storage)) {
            occupiedSpace += file.getSize();
        }
        return occupiedSpace;
    }
}
