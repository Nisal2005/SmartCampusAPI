package com.smartcampus;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {
    public static void main(String[] args) {
        ResourceConfig config = new ResourceConfig()
                .packages("com.smartcampus");

        GrizzlyHttpServerFactory.createHttpServer(
                URI.create("http://localhost:8080/api/v1/"),
                config
        );

        System.out.println("Server started...");
        
        try {
            // Keeps the server running
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
