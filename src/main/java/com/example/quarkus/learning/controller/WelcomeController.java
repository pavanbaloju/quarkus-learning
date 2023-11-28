package com.example.quarkus.learning.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@Path("/welcome")
@ApplicationScoped
public class WelcomeController {

    @Inject
    Logger logger;

    @ConfigProperty(name = "greeting.name")
    String name;

    @ConfigProperty(name = "greeting.message")
    String message;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser() {
        String greeting = "Hello " + name + ". " + message;
        logger.info(greeting);
        return greeting;
    }
}
