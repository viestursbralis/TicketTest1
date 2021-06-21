package com.example.Ticket1;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/test")
@Controller
public class TestController {



        @Path("/set")
        @GET
        public String testSetMethod(HttpServletRequest request)
        {
            request.getSession().setAttribute("testVariable", "Test Values!!");
            return "testJsp";
        }

    @Path("/get")
    @GET
    public String testGetMethod(HttpServletRequest request)
    {
       String tjp =  (String)request.getSession().getAttribute("testVariable");
        return tjp;
    }
}
