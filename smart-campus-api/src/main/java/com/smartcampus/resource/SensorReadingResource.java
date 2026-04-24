package com.smartcampus.resource;

import com.smartcampus.db.DataStore;
import com.smartcampus.model.Sensor;
import com.smartcampus.model.SensorReading;
import com.smartcampus.exception.SensorUnavailableException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.*;

public class SensorReadingResource {

    private static Map<String, List<SensorReading>> readings = new HashMap<>();

    @GET
    public Response getReadings(@PathParam("id") String sensorId) {
        return Response.ok(readings.getOrDefault(sensorId, new ArrayList<>())).build();
    }

    @POST
    public Response addReading(@PathParam("id") String sensorId, SensorReading reading) {

        Sensor sensor = DataStore.sensors.get(sensorId);

        if (sensor == null) {
            return Response.status(404).build();
        }

        if ("MAINTENANCE".equals(sensor.getStatus())) {
            throw new SensorUnavailableException("Sensor under maintenance");
        }

        readings.putIfAbsent(sensorId, new ArrayList<>());
        readings.get(sensorId).add(reading);

        sensor.setCurrentValue(reading.getValue());

        return Response.status(201).build();
    }
}
