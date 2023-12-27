package com.hprtech.resource;

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileList(){
        return Response.ok(mobileList).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileById(@PathParam("id") int id){
        Optional<Mobile> first = mobileList.stream()
                .filter(mobile -> mobile.getId() == id).findFirst();
        return Response.ok(first.get()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMobile(Mobile mobile){
        mobileList.add(mobile);
        return Response.ok(mobile).build();
    }

    @PUT
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
//
//    @PUT
//    @Path("/{oldMobileName}")
//    @Consumes(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response updateMobile(@PathParam("oldMobileName") String oldMobileName, @QueryParam("newMobileName") String newMobileName){
//       mobileList= mobileList.stream()
//                .map(mobile -> {
//                    if (mobile.equals(oldMobileName)) {
//                        return newMobileName;
//                    } else {
//                        return mobile;
//                    }
//                }).collect(Collectors.toList());
//        return Response.ok(mobileList).build();
//    }
//
//    @DELETE
//    @Path("/{oldMobileName}")
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response deleteMobile(@PathParam("oldMobileName") String oldMobileName){
//        boolean isRemoved = mobileList.remove(oldMobileName);
//        if(isRemoved){
//            return Response.ok(mobileList).build();
//        }else{
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
//    }

    @DELETE
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
