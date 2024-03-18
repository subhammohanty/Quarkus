package com.hprtech.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/mobile")
public class MobileResource {

    List<Mobile> mobileList = new ArrayList<>();

    @GET
    @RolesAllowed({"student","professor","admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileList(){
        return Response.ok(mobileList).build();
    }

    @GET
    @RolesAllowed({"student","professor","admin"})
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileById(@PathParam("id") int id){
        Optional<Mobile> first = mobileList.stream()
                .filter(mobile -> mobile.getId() == id).findFirst();
        return Response.ok(first.get()).build();
    }

    @POST
    @RolesAllowed({"professor","admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMobile(Mobile mobile){
        mobileList.add(mobile);
        return Response.ok(mobile).build();
    }

    @PUT
    @RolesAllowed({"professor","admin"})
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMobile(@PathParam("id") int id, Mobile mobileToUpdate){
        mobileList = mobileList.stream().map(mobile -> {
            if(mobile.getId() == id){
                return mobileToUpdate;
            }else{
                return mobile;
            }
        }).collect(Collectors.toList());
        return Response.ok(mobileList).build();
    }
    @DELETE
    @RolesAllowed({"admin"})
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMobile(@PathParam("id") int id){
        Optional<Mobile> mobileToDelete = mobileList.stream()
                .filter(mobile -> mobile.getId() == id).findFirst();
        if(mobileToDelete.isPresent()){
            mobileList.remove(mobileToDelete.get());
            return Response.ok(mobileList).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
