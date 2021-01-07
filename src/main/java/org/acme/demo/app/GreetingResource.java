package org.acme.demo.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import org.acme.demo.app.service.GreetingService;
import javax.ws.rs.PathParam;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.util.Optional;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService service;

    @ConfigProperty(name = "greeting.message")
    String message;

    @ConfigProperty(name = "greeting.suffix", defaultValue="!")
    String suffix;

    @ConfigProperty(name = "greeting.name")
    Optional<String> name;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(@PathParam("name") String name) {
        return service.greeting(name);
    }

        @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message + " " + name.orElse("world") + suffix;
    }
}