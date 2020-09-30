package com.Lesson6.DAO;

import com.Lesson6.Model.Passenger;

import java.util.List;

public interface PassengerDAO extends GeneralDAO<Passenger>{

    List getOldPlanes();

}
