package org.example.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@Path("")
@SecurityScheme(scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT")
public class Resource {

    @GET
    @RolesAllowed({"teacher", "admin"})
    @Path("/getList")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCourseList(){
        return Response.ok("Here are course list").build();
    }


    @GET
    @RolesAllowed({"admin"})
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCourseList(){
        return Response.ok("Here are course list to be deleted").build();
    }
}
