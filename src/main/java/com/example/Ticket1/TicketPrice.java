package com.example.Ticket1;


import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Controller
@Path("/")
public class TicketPrice {

    @Path("ticketprice")
    @GET
    @Produces("application/json")
    @ResponseBody
    public JSONObject getTicketPrice(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Vilnius", (double)10);
        jsonObject.put("Tallinn", (double)20);
        jsonObject.put("Riga", (double)30);
        return jsonObject;


    }



}
