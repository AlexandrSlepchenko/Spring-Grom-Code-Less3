package com.Lesson6.DAO;

import com.Lesson6.Model.Flight;

import java.util.List;

public interface FlightDAO extends GeneralDAO<Flight>{

    List mostPopularTo();
    List mostPopularFrom();
}
