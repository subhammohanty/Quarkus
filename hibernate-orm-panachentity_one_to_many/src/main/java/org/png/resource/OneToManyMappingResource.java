package org.png.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.png.entity.SimCard;
import org.png.entity.User;
import org.png.repository.SimCardRepository;
import org.png.repository.UserRepository;

import java.util.List;

@Path("/OneToMany")
public class OneToManyMappingResource {

    @Inject
    private UserRepository userRepository;

    @GET
    @Path("/")
    @Transactional
    public Response saveCitizen(){
        User user = new User();
        user.setName("Subham");
        user.setGender("M");

        SimCard s1 = new SimCard();
        s1.setUser(user);
        s1.setNumber(9999L);
        s1.setProvider("Jio");
        s1.setActive(true);

        SimCard s2 = new SimCard();
        s2.setUser(user);
        s2.setNumber(9657L);
        s2.setProvider("Airtel");
        s2.setActive(true);

        SimCard s3 = new SimCard();
        s3.setUser(user);
        s3.setNumber(809329L);
        s3.setProvider("Vodafoneo");
        s3.setActive(true);

        user.setSimCards(List.of(s1,s2,s3));

        userRepository.persist(user);
        if(userRepository.isPersistent(user)){
            return Response.ok(new String("User Saved Successfully !!")).build();
        }else{
            return Response.ok(new String("Saving User Failed !!")).build();
        }

    }
}
