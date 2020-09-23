package com.Lesson3.Controller;

import com.Lesson3.Model.ArrayWrapper;
import com.Lesson3.Model.File;
import com.Lesson3.Model.Storage;
import com.Lesson3.Service.FileService;
import com.Lesson3.Service.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@org.springframework.stereotype.Controller
public class FileController {

    FileService fileService;
    StorageService storageService;

    @Autowired
    public FileController(FileService fileService, StorageService storageService) {
        this.fileService = fileService;
        this.storageService = storageService;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "put", produces = "text/plain")
    public @ResponseBody
    void put(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (BufferedReader br = req.getReader()) {

            ArrayWrapper wrapper = arrayMapper(br);
            int[] array = wrapper.getArray();
            Storage storage = storageService.findById(array[0]);
            com.Lesson3.Model.File file = fileService.findById(array[1]);

            fileService.put(storage, file);

        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "transferAll", produces = "text/plain")
    public @ResponseBody
    void transferAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (BufferedReader br = req.getReader()) {

            ArrayWrapper wrapper = arrayMapper(br);
            int[] array = wrapper.getArray();
            Storage storageFrom = storageService.findById(array[0]);
            Storage storageTo = storageService.findById(array[1]);

            fileService.transferAll(storageFrom, storageTo);
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/transferFile", produces = "text/plain")
    public @ResponseBody
    void transferFile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (BufferedReader br = req.getReader()) {

            ArrayWrapper wrapper = arrayMapper(br);
            int[] array = wrapper.getArray();
            Storage storage = storageService.findById(array[0]);
            Long fileId = Long.valueOf((array[1]));

            fileService.transferFile(storage, fileId);
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text/plain")
    public @ResponseBody
    void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (BufferedReader br = req.getReader()) {

            ArrayWrapper wrapper = arrayMapper(br);
            int[] array = wrapper.getArray();
            Storage storage = storageService.findById(array[0]);
            com.Lesson3.Model.File file = fileService.findById(array[1]);

            fileService.delete(storage, file);
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "GetFile", produces = "text/plain")
    public @ResponseBody
    void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.getWriter().println(fileService.findById(Long.valueOf(req.getParameter("id"))).toString());
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "PostFile", produces = "text/plain")
    public @ResponseBody
    void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        com.Lesson3.Model.File file;
        try (BufferedReader br = req.getReader()) {
            file = mapper(br);
            fileService.save(file);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "PutFile", produces = "text/plain")
    public @ResponseBody
    void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        com.Lesson3.Model.File file;
        try (BufferedReader br = req.getReader()) {
            file = mapper(br);
            fileService.update(file);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "DeleteFile", produces = "text/plain")
    public @ResponseBody
    void deleteFile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            fileService.deleteById(Long.parseLong(req.getParameter("id")));
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    public com.Lesson3.Model.File mapper(BufferedReader br) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(br, File.class);
    }

    public ArrayWrapper arrayMapper(BufferedReader br) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(br, ArrayWrapper.class);
    }

}
