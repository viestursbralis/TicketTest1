package com.example.Ticket1;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class VAT {
    @Path("vat")
    //@Consumes(MediaType.APPLICATION_JSON_VALUE)
    @GET
    @Produces("application/json")
    @ResponseBody
    public JSONObject getVAT(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("vat", (double)0.21);
        return jsonObject;
    }

}
