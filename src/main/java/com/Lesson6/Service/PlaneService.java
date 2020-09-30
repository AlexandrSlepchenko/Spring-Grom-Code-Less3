package com.Lesson6.Service;

import com.Lesson6.Model.Plane;

public interface PlaneService {

    Plane savePlane(Plane plane);
    Plane updatePlane(Plane plane);
    void deletePlane(Long id);
    Plane findPlaneById(Long id);

}
