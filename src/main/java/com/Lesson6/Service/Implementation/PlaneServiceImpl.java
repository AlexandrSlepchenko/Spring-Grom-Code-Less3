package com.Lesson6.Service.Implementation;

import com.Lesson6.DAO.PlaneDAO;
import com.Lesson6.Model.Plane;
import com.Lesson6.Service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaneServiceImpl implements PlaneService {

    PlaneDAO planeDAO;

    @Autowired
    public PlaneServiceImpl(PlaneDAO planeDAO) {
        this.planeDAO = planeDAO;
    }

    public Plane savePlane(Plane plane) {
        return planeDAO.save(plane);
    }

    public Plane updatePlane(Plane plane) {
        return planeDAO.update(plane);
    }

    public void deletePlane(Long id) {
        planeDAO.deleteById(id);
    }

    public Plane findPlaneById(Long id) {
        return planeDAO.findById(id);
    }
}
