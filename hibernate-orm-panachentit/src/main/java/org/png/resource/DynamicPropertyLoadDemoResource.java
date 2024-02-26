package org.png.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.xml.namespace.QName;

@Path("/config")
@Produces(MediaType.APPLICATION_JSON)
public class DynamicPropertyLoadDemoResource {

    @ConfigProperty(name = "interest_Rate", defaultValue = "20")
    private int interest;

    @GET
    @Path("/calInterest/{amount}")
    public Response calInterest(@PathParam("amount") int amount){
        int result = (amount * 2 * interest) / 100;
        return Response.ok(result).build();
    }

    //To load the property dynamically
    @GET
    @Path("/calInterest/{branch}/{amount}")
    public Response calInterestRateDynamically(@PathParam("branch") String branch,@PathParam("amount") int amount){
//        Integer value = ConfigProvider.getConfig().getValue(branch.toLowerCase() + "_interest_rate", Integer.class);
        //To avoid if value passed not present in config file
        Integer value = ConfigProvider.getConfig()
                .getOptionalValue(branch.toLowerCase() + "_interest_rate", Integer.class)
                .orElse(5);
        int result = (amount * 2 * value.intValue()) / 100;
        return Response.ok(result).build();
    }
}
