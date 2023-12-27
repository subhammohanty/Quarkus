package org.png.resource;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.png.entity.Aadhar;
import org.png.entity.Citizen;
import org.png.repository.AadharRepository;
import org.png.repository.CitizenRepository;

import java.util.List;

@Path("/citizen")

public class CitizenResource {

    @Inject
    private CitizenRepository citizenRepository;

    @Inject
    private AadharRepository aadharRepository;

    @GET
    @Path("/save")
    @Transactional
    public void saveCitizen(){

        Citizen citizen = new Citizen();
        citizen.setName("Subham");
        citizen.setGender("M");

        Aadhar aadhar = new Aadhar();
        aadhar.setAadharNumber(222L);
        aadhar.setCompany("UIDDI");
        aadhar.setCitizen(citizen);

        citizen.setAadhar(aadhar);
        citizenRepository.persist(citizen);
    }

    @GET
    @Path("/")
    public Response getAllCitizens(){
        List<Citizen> citizens = citizenRepository.listAll();
        return Response.ok(citizens).build();
    }
}
