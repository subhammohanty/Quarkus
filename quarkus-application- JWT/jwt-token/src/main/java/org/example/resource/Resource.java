package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("")
public class Resource {
    @Inject
    private TokenService tokenService;

    @GET
    @Path("/getToken")
    @Produces(MediaType.TEXT_PLAIN)
    public String getToken(){
        return tokenService.generateToken();
    }
}
