package org.png.resource;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;
import org.png.dto.projection.SimCardProjection;
import org.png.entity.SimCard;
import org.png.repository.SimCardRepository;

import java.util.List;
import java.util.Optional;

@Path("/")
public class Resource {

    public static final Logger logger = Logger.getLogger(Resource.class);
    @Inject
    private SimCardRepository simCardRepository;

    @POST
    @Path("/saveSim")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveSimCard(@RequestBody SimCard simCard) {
        logger.info("Inside saveSimCard :: saveSimCard()");
        simCardRepository.persist(simCard);
        if (simCardRepository.isPersistent(simCard)) {
            return Response.ok(new String("Saved Successfully !!")).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSimCards() {
        List<SimCard> simCardList = simCardRepository.listAll();
        return Response.ok(simCardList).build();
    }

    @GET
    @Path("/getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimCardById(@PathParam("id") Long id){
        SimCard simCard = simCardRepository.findById(id);
        return Response.ok(simCard).build();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countSimCards(){
        long count = simCardRepository.count();
        return Response.ok(count).build();
    }

    @GET
    @Path("/providerSimCards/{provider}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response providerListSimCard(@PathParam("provider") String provider){
        List<SimCard> simCards = simCardRepository.list("provider", provider);
        return Response.ok(simCards).build();
    }

    @GET
    @Path("/activeSimCards")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isActiveSimCards(){
        List<SimCard> simCards = simCardRepository.list("isActive", true);
        return Response.ok(simCards).build();
    }

    @GET
    @Path("/findByIdOptional/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdOptional(@PathParam("id") Long id) {
        Optional<SimCard> simCardOptional = simCardRepository.findByIdOptional(id);
        if (simCardOptional.isPresent()) {
            return Response.ok(simCardOptional.get()).build();
        } else {
            return Response.noContent().build();

        }
    }

    @GET
    @Path("/conditionalCount/{provider}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response conditionalCountSimCard(@PathParam("provider") String provider){
        long count = simCardRepository.count("provider", provider);
        return Response.ok(count).build();
    }

    @DELETE
    @Path("/conditionalDelete/{provider}")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response conditionalDeleteSimCard(@PathParam("provider") String provider){
        long deleteCount = simCardRepository.delete("provider", provider);
        return Response.ok(deleteCount).build();
    }

    @DELETE
    @Path("/deleteById/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response deleteById(@PathParam("id") Long id){
        boolean isDeleted = simCardRepository.deleteById(id);
        if(isDeleted){
            return Response.ok(new String("Deleted Suceessfully!! ")).build();
        }else{
            return Response.ok(new String("Something Went wrong !!")).build();
        }
    }

    @PUT
    @Path("/update/{id}/{provider}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateSim(@PathParam("id") Long id, @PathParam("provider") String provider){
        int update = simCardRepository.update("provider = ?1 where id = ?2", provider, id);
        if(update == 1){
            return Response.ok("Sim Card Provider Updated Successfully !!").build();
        }else{
            return Response.ok("Something went wrong !!").build();
        }
    }

    @GET
    @Path("/sortBy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortBySimCard(){
        List<SimCard> simCardList = simCardRepository.list("isActive", Sort.descending("provider"), false);
        return Response.ok(simCardList).build();
    }

    @GET
    @Path("/findByPageNo/{pageNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response paginationSimCard(@PathParam("pageNo") int pageNo){
        PanacheQuery<SimCard> simCardPanacheList = simCardRepository.findAll();
        List<SimCard> simCards = simCardPanacheList.page(Page.of(pageNo, 5)).list();
        return Response.ok(simCards).build();
    }

    //ToGet only desired fields
    @GET
    @Path("/projection")
    @Produces(MediaType.APPLICATION_JSON)
    public Response projectionSimCard(){
        List<SimCardProjection> simCardList = simCardRepository.findAll().project(SimCardProjection.class).list();
        return Response.ok(simCardList).build();
    }

//    @GET
//    @Path("save")
//    @Transactional
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response saveData(){
//        String[] providers = {"Jio","Airtel","Vodafone","BSNL","Aircel"};
//        for(long i=0;i<20L;i++){
//            SimCard simCard = new SimCard();
//            simCard.setNumber(8093294508L+i);
//            simCard.setProvider(providers[(int)i% providers.length]);
//            simCard.setActive(i/3L==0);
//
//            simCardRepository.persist(simCard);
//            if(simCardRepository.isPersistent(simCard)){
//                System.out.println("Saved Sucessfully for: "+simCard);
//            }else{
//                System.out.println("Saving failed for: "+simCard);
//            }
//        }
//        return Response.ok(new String("Saved Successfully : ")).build();
//    }
//
//    @GET
//    @Path("getAllSimCards")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllData(){
//        List<SimCard> simCardList = simCardRepository.listAll();
//        return Response.ok(simCardList).build();
//    }
}
