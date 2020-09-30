package com.Lesson6.Service;

import com.Lesson6.Model.Passenger;

public interface PassengerService {

    Passenger savePassenger(Passenger passenger);
    Passenger updatePassenger(Passenger passenger);
    void deletePassenger(Long id);
    Passenger findPassengerById(Long id);

}
