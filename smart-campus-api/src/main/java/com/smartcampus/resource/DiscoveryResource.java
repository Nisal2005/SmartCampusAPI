package com.smartcampus.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.HashMap;
import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DiscoveryResource {

    @GET
    public Response getInfo() {

        Map<String, Object> data = new HashMap<>();
        data.put("version", "v1");
        data.put("contact", "admin@smartcampus.com");

        Map<String, String> links = new HashMap<>();
        links.put("rooms", "/api/v1/rooms");
        links.put("sensors", "/api/v1/sensors");

        data.put("resources", links);

        return Response.ok(data).build();
    }
}
