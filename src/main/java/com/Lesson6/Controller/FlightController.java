package com.Lesson6.Controller;

import com.Lesson6.Model.Flight;
import com.Lesson6.Service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Controller
@RequestMapping("/flight")
public class FlightController {

    FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @GetMapping("/Get")
    ResponseEntity<String> get(
            @RequestParam("id") long id) {
        try {
            return new ResponseEntity<>(flightService.findFlightById(id).toString(), HttpStatus.OK);
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
            flightService.saveFlight(new ObjectMapper().readValue(dataStream, Flight.class));
            return "Flight save" ;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/Put")
    public @ResponseBody
    String update(InputStream dataStream){
        try {
            flightService.updateFlight(new ObjectMapper().readValue(dataStream, Flight.class));
            return "Flight update";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/Delete")
    ResponseEntity<String> delete(
            @RequestParam("id") long id) {
        try{
            flightService.deleteFlight(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/PostFlight")
    public @ResponseBody
    void doPost(HttpServletRequest req) throws IOException {
        Flight flight;
        try (BufferedReader br = req.getReader()) {
            flight = mapper(br);
            flightService.saveFlight(flight);
        }
    }

    public Flight mapper(BufferedReader br) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(br, Flight.class);
    }

}
