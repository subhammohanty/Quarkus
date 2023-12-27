package org.png.resource;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.png.entity.Laptop;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/laptop")
public class LaptopResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLaptop() {
        List<Laptop> laptopList = Laptop.listAll();
        return Response.ok(laptopList).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveLaptop(Laptop laptop) {
        Laptop.persist(laptop);
        if (laptop.isPersistent()) {
            return Response.created(URI.create("/laptop/" + laptop.id)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLaptopById(@PathParam("id") Long id) {
        Laptop findById = Laptop.findById(id);
        return Response.ok(findById).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLaptop(@PathParam("id") Long id, Laptop laptop) {
        Optional<Laptop> byIdOptional = Laptop.findByIdOptional(id);
        if (byIdOptional.isPresent()) {
            Laptop dbLaptop = byIdOptional.get();
            dbLaptop.setName(laptop.getName());
            dbLaptop.setBrand(laptop.getBrand());
            dbLaptop.setRam(laptop.getRam());
            dbLaptop.setExternalStorage(laptop.getExternalStorage());
            if (dbLaptop.isPersistent()) {
                return Response.created(URI.create("/laptop/" + id)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLaptop(@PathParam("id") Long id) {
        boolean isDeleted = Laptop.deleteById(id);
        if (isDeleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


}
