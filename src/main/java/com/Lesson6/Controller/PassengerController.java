package com.Lesson6.Controller;

import com.Lesson6.Model.Passenger;
import com.Lesson6.Service.PassengerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.Arrays;

@Controller
@RequestMapping("/passenger")
public class PassengerController {

    PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService){
        this.passengerService = passengerService;
    }

    @GetMapping("/Get")
    ResponseEntity<String> get(
            @RequestParam("id") long id) {
        try {
            return new ResponseEntity<>(passengerService.findPassengerById(id).toString(), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/Post")
    public @ResponseBody
    String save(InputStream dataStream){
        try {
            passengerService.savePassenger(new ObjectMapper().readValue(dataStream, Passenger.class));
            return "Flight save" ;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/Put")
    public @ResponseBody
    String update(InputStream dataStream){
        try {
            passengerService.updatePassenger(new ObjectMapper().readValue(dataStream, Passenger.class));
            return "Flight update";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/Delete")
    ResponseEntity<String> delete(
            @RequestParam("id") long id) {
        try{
            passengerService.deletePassenger(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
