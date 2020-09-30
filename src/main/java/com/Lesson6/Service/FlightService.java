package com.Lesson6.Service;

import com.Lesson6.Model.Flight;

public interface FlightService {

    Flight saveFlight(Flight flight);

    Flight updateFlight(Flight flight);

    void deleteFlight(Long id);

    Flight findFlightById(Long id);
}

