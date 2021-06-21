package com.example.Ticket1;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.ws.rs.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;

//import static org.springframework.http.MediaType.TEXT_PLAIN;

@Path("/")
public class UtilResource {



    @Path("yes")
    @GET
    @Produces("text/plain")
    public String hellyes(){

        return "Rezultāts";
    }




    @Path("hello-world")
    @POST
    @Consumes(MediaType.ALL_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject hello(@RequestBody Passenger passenger) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", passenger.getType());
        jsonObject.put("countOfBags", passenger.getCountOfBags());


        return jsonObject;
    }

    @Path("hello-post")
    @POST
    //@Consumes("application/json")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces("application/json")
    @ResponseBody
    public JSONObject hellopost(@RequestBody Passenger passenger) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", passenger.getType());
        jsonObject.put("countOfBags", passenger.getCountOfBags());


        return jsonObject;
    }

    @Path("passenger_list")
    @POST
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces("application/json")
    @ResponseBody
    public JSONObject passengerList(@RequestBody List<Passenger> passengers) {

int count = passengers.size();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("size", count);
        JSONObject vat = getVAT();
        String vatstring  = vat.get("vat").toString();
        jsonObject.put("vat", vatstring);

//jsonObject.put("destination", destination.getDest());

        return jsonObject;
    }


    public String priceCalculator(List<Passenger>passengers){
        JSONObject jsonObject = new JSONObject();

        for(Passenger p:passengers){
            if(p.getType().equals("adult")){

            }


        }
return "";
    }

    public JSONObject getVAT(){
String ret  = "";
        JSONObject jsonObject = new JSONObject();
        ProcessBuilder pb = new ProcessBuilder(
                "curl",
                 "-H \"Accept: application/json\"",
                "http://localhost:8080/TicketTest1-1.0-SNAPSHOT/api/vat");
try {
    pb.redirectErrorStream(true);
    Process p = pb.start();
    InputStream is = p.getInputStream();

    String line;
    BufferedInputStream bis = new BufferedInputStream(is);
    System.out.println(bis);
    ret = IOUtils.toString(bis, "UTF-8");
    ret = ret.substring(ret.indexOf("{") + 1);
    ret = ret.substring(0, ret.indexOf("}"));
ret = ret.replaceAll("\\\\", " ");
ret = ret.replaceAll("\"", "");
    jsonObject.put("ret3", ret);//"ret3": "\"vat\":0.21"
String[]arr = ret.split(":");
    //JSONParser jsonParser = new JSONParser();
   //jsonObject = (JSONObject)jsonParser.parse(ret);
jsonObject.put(arr[0], Double.parseDouble(arr[1]));


}catch(Exception ex){}
return jsonObject;
    }

    public JSONObject getTicketPrice(){
        String ret  = "";
        JSONObject jsonObject = new JSONObject();
        ProcessBuilder pb = new ProcessBuilder(
                "curl",
                "-H \"Accept: application/json\"",
                "http://localhost:8080/TicketTest1-1.0-SNAPSHOT/api/ticketprice");
        try {
            pb.redirectErrorStream(true);
            Process p = pb.start();
            InputStream is = p.getInputStream();

            String line;
            BufferedInputStream bis = new BufferedInputStream(is);
            System.out.println(bis);
            ret = IOUtils.toString(bis, "UTF-8");
            ret = ret.substring(ret.indexOf("{") + 1);
            ret = ret.substring(0, ret.indexOf("}"));
            ret = ret.replaceAll("\\\\", "");
            ret = ret.replaceAll("\"", "");
            jsonObject.put("ret3", ret);//"ret3": "\"vat\":0.21"
            String[]arr = ret.split(",");
           for(String a : arr){
               String[]data = a.split(":");
               jsonObject.put(data[0], Double.parseDouble(data[1]));
           }




        }catch(Exception ex){}
        return jsonObject;
    }


}