package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.CitizenDto;
import org.acme.entity.Citizen;
import org.acme.mapper.CitizenMapper;
import org.acme.repository.CitizenRepository;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

    @Inject
    private CitizenRepository citizenRepository;

    @Inject
    private CitizenMapper citizenMapper;

    @Path("")
    @GET
    public Response getAllCitizen(){
        List<Citizen> citizens = citizenRepository.listAll();
        if(citizens != null){
            return Response.ok(citizens).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @GET
    public Response getCitizenById(@PathParam("id") Long id){
        Citizen citizen = citizenRepository.findById(id);
        CitizenDto dto = citizenMapper.toDTO(citizen);
        if(dto != null){
            dto.setFullName("Mr " + dto.getFullName());
            return Response.ok(dto).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("")
    @POST
    @Transactional
    public Response saveCitizen(@RequestBody CitizenDto citizenDto){
        Citizen dao = citizenMapper.toDAO(citizenDto);
        citizenRepository.persist(dao);
        return Response.ok(dao).build();
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public Response updateCitiZen(@PathParam("id") Long id, @RequestBody CitizenDto citizenDto){
        Citizen byId = citizenRepository.findById(id);

        if(byId != null){
            Citizen dao = citizenMapper.toDAO(citizenDto);
            citizenMapper.merge(byId, dao);
            citizenRepository.persist(byId);
            return Response.ok(dao).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
