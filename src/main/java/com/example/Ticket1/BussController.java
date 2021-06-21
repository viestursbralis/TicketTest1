package com.example.Ticket1;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Path("/buss")
public class BussController {
    @Path("/getvat")
    @GET
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces("application/json")
    @ResponseBody
    public JSONObject getVAT(@RequestBody Buss buss){
        UtilResource hell = new UtilResource();
        return hell.getVAT();


    }



    @Path("/calculate")
    @POST
    @Consumes(MediaType.ALL_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject setBuss(@RequestBody Buss buss){
        UtilResource hell = new UtilResource();
        String dest = buss.getDestination();
        JSONObject priceObject = hell.getTicketPrice();
        double price = (double)priceObject.get(dest);
        JSONObject vatObject = hell.getVAT();
        double vat  = (double)vatObject.get("vat");
        String vatDecimal = vat*100+"%";
        JSONObject ticketObject = new JSONObject();
        JSONArray ticketArray = new JSONArray();
        double totalPrice = 0;

       // Adult(10 EUR +21%) = 12.10 EUR
        //Two bags (2x10 EURx30%+21%) = 7.26 EUR

        List<Passenger> listOfPassengers = buss.getPassengers();

        for(Passenger pass:listOfPassengers){
            JSONObject t = new JSONObject();
            String type = pass.getType();
            double countOfBags = pass.getCountOfBags();
            String typeData = "";
            double typePrice = 0;
            if(type.equals("adult")) {
                typeData = type + " (" + price + " EUR +" + vatDecimal + ") = ";
                typePrice= price + (price * vat);
            }else if(type.equals("child")){
                typeData = type + " (" + price + " EUR *50% +" + vatDecimal + ") = ";
                typePrice= price*0.5 + (price*0.5 * vat);

            }

           t.put(typeData, typePrice);
           String bagsData = countOfBags + " bags ("+countOfBags+"x"+price+" EUR * 30% + "+vatDecimal+") = ";
           double bagsPrice = (countOfBags*price*0.3)+(countOfBags*price*0.3)*vat;
            t.put(bagsData, bagsPrice);
            ticketArray.add(t);
            totalPrice = totalPrice+typePrice+bagsPrice;
        }
ticketObject.put("Ticket price", ticketArray);
        ticketObject.put("dest", dest);

        ticketObject.put("price", totalPrice);
return ticketObject;

    }


    @Path("/ticketprice")
    @GET
    @Produces("application/json")
    @ResponseBody
    public JSONObject getTicketPrice(){
        String ret  = "";
        JSONObject jsonObject = new JSONObject();
        ProcessBuilder pb = new ProcessBuilder(
                "curl",
                "-X GET -i" ,
                "http://localhost:8080/TicketTest1-1.0-SNAPSHOT/api/ticketprice");
        try {
            pb.redirectErrorStream(true);
            Process p = pb.start();
            InputStream is = p.getInputStream();
            jsonObject.put("bis", is);
            String line;
            BufferedInputStream bis = new BufferedInputStream(is);
            System.out.println(bis);

            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(is, "UTF-8"));




        }catch(Exception ex){}
        return jsonObject;
    }
}
