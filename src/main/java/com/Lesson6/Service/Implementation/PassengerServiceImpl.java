package com.Lesson6.Service.Implementation;

import com.Lesson6.DAO.PassengerDAO;
import com.Lesson6.Model.Passenger;
import com.Lesson6.Service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl implements PassengerService {
    PassengerDAO passengerDAO;

    @Autowired
    public PassengerServiceImpl (PassengerDAO passengerDAO){
        this.passengerDAO = passengerDAO;
    }

    public Passenger savePassenger(Passenger passenger){
        return passengerDAO.save(passenger);
    }

    public Passenger updatePassenger(Passenger passenger){
        return passengerDAO.update(passenger);
    }

    public void deletePassenger(Long id){
        passengerDAO.deleteById(id);
    }

    public Passenger findPassengerById(Long id){
        return passengerDAO.findById(id);
    }
}
