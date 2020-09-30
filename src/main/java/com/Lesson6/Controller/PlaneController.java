package com.Lesson6.Controller;

import com.Lesson6.Model.Plane;
import com.Lesson6.Service.PlaneService;
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
import java.io.InputStream;
import java.util.Arrays;

@Controller
@RequestMapping("/plane")
public class PlaneController {

    PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order", produces = "text/plain")
    public @ResponseBody
    String test() {
        return "ok";
    }

    @GetMapping("/Get")
    ResponseEntity<String> get(
            @RequestParam("id") long id) {
        try {
            return new ResponseEntity<>(planeService.findPlaneById(id).toString(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/Post")
    public @ResponseBody
    String save(InputStream dataStream) {
        try {
            planeService.savePlane(new ObjectMapper().readValue(dataStream, Plane.class));
            return "Flight save";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/Put")
    public @ResponseBody
    String update(InputStream dataStream) {
        try {
            planeService.updatePlane(new ObjectMapper().readValue(dataStream, Plane.class));
            return "Flight update";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/Delete")
    ResponseEntity<String> delete(
            @RequestParam("id") long id) {
        try {
            planeService.deletePlane(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "GetPlane", produces = "text/plain")
    public @ResponseBody
    void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.getWriter().println(planeService.findPlaneById(Long.valueOf(req.getParameter("id"))).toString());
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "PostPlane", produces = "text/plain")
    public @ResponseBody
    void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Plane plane;
        try (BufferedReader br = req.getReader()) {
            plane = mapper(br);
            planeService.savePlane(plane);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "PutPlane", produces = "text/plain")
    public @ResponseBody
    void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Plane plane;
        try (BufferedReader br = req.getReader()) {
            plane = mapper(br);
            planeService.updatePlane(plane);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "DeletePlane", produces = "text/plain")
    public @ResponseBody
    void deletePlane(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            planeService.deletePlane(Long.parseLong(req.getParameter("id")));
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }


    @DeleteMapping("/ParamDeletePlane")
    ResponseEntity<String> doDelete(
            @RequestParam("longId") long id) {
        System.out.println(id);
        try{
            planeService.deletePlane(id);
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public Plane mapper(BufferedReader br) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(br, Plane.class);
    }

//    [org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException(HibernateJpaDialect.java:278),
//            org.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:244),
//            org.springframework.orm.jpa.JpaTransactionManager.doCommit(JpaTransactionManager.java:521),
//            org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:761),
//            org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:730),
//            org.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:487),
//            org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:291),
//            org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96),
//            org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179),
//            org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:213),
//            com.sun.proxy.$Proxy67.deleteById(Unknown Source),
//            com.Lesson6.Service.Implementation.PlaneServiceImpl.deletePlane(PlaneServiceImpl.java:28),
//            com.Lesson6.Controller.PlaneController.delete(PlaneController.java:74),
//            sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method),
//            sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62),
//            sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43),
//            java.lang.reflect.Method.invoke(Method.java:498),
//            org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:221),
//            org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136),
//            org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:114),
//            org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827),
//            org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738),
//            org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85),
//            org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963),
//            org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:897),
//            org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970),
//            org.springframework.web.servlet.FrameworkServlet.doDelete(FrameworkServlet.java:894),
//            javax.servlet.http.HttpServlet.service(HttpServlet.java:658),
//            org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846),
//            javax.servlet.http.HttpServlet.service(HttpServlet.java:733),
//            org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231),
//            org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166),
//            org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53),
//            org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193),
//            org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166),
//            org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202),
//            org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96),
//            org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541),
//            org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139),
//            org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92),
//            org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:690),
//            org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74),
//            org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343),
//            org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:373),
//            org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65),
//            org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:868),
//            org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1589),
//            org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49),
//            java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149),
//            java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624),
//            org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61),
//            java.lang.Thread.run(Thread.java:748)]
}

