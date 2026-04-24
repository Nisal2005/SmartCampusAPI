package com.smartcampus.resource;

import com.smartcampus.db.DataStore;
import com.smartcampus.model.Sensor;
import com.smartcampus.model.Room;
import com.smartcampus.exception.LinkedResourceNotFoundException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.stream.Collectors;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    @POST
    public Response createSensor(Sensor sensor) {

        Room room = DataStore.rooms.get(sensor.getRoomId());

        if (room == null) {
            throw new LinkedResourceNotFoundException("Room ID does not exist");
        }

        DataStore.sensors.put(sensor.getId(), sensor);
        room.getSensorIds().add(sensor.getId());

        return Response.status(201).entity(sensor).build();
    }

    @GET
    public Response getSensors(@QueryParam("type") String type) {

        if (type == null) {
            return Response.ok(DataStore.sensors.values()).build();
        }

        return Response.ok(
                DataStore.sensors.values()
                        .stream()
                        .filter(s -> s.getType().equalsIgnoreCase(type))
                        .collect(Collectors.toList())
        ).build();
    }

    @Path("/{id}/readings")
    public SensorReadingResource getReadings() {
        return new SensorReadingResource();
    }
}
