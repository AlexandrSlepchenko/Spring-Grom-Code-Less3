package com.Lesson6.DAO;

import com.Lesson6.Model.Plane;

import java.util.List;

public interface PlaneDAO extends GeneralDAO<Plane>{

    List getOldPlanes();
}

