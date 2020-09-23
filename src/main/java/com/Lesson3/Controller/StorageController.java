package com.Lesson3.Controller;

import com.Lesson3.Model.Storage;

import com.Lesson3.Service.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Controller
public class StorageController {
    StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "GetStorage", produces = "text/plain")
    public @ResponseBody
    void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.getWriter().println(storageService.findById(Long.valueOf(req.getParameter("id"))).toString());
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "PostStorage", produces = "text/plain")
    public @ResponseBody
    void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Storage storage;
        try (BufferedReader br = req.getReader()) {
            storage = mapper(br);
            storageService.save(storage);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "PutStorage", produces = "text/plain")
    public @ResponseBody
    void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Storage storage;
        try (BufferedReader br = req.getReader()) {
            storage = mapper(br);
            storageService.update(storage);
        }
    }


    @DeleteMapping("/ParamDeleteStorage")
    ResponseEntity<String> delete(
            @RequestParam("longId") long id) {
        System.out.println(id);
        try{
            storageService.deleteById(id);
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public Storage mapper(BufferedReader br) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(br, Storage.class);
    }
}
