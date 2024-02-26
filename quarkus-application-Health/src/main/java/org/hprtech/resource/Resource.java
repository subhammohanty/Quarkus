package org.hprtech.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.print.attribute.standard.Media;

@Path("/")
public class Resource {

    private long highestPrimeNumberSoFar = 3;

    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(){
        return Response.ok("Hello From Quarkus").build();
    }

    @POST
    @Path("/{number}")
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "CountOf_CheckIfPrime", description = "How many time this method/api is called")
    @Timed(name = "TimeTaken_CheckIfPrime", description = "How much time api take to respond", unit = MetricUnits.MILLISECONDS)
    @Metered(name = "Metered_CHeckIfPrime", description = "How Freq this method is called")
    public String checkIfPrime(@PathParam("number") long number){
        if(number < 1){
            return "Only natural numbers can be prime numbers.";
        }
        if(number == 1){
            return "1 is not prime.";
        }
        if(number == 2){
            return "2 is prime";
        }
        if(number % 2 == 0){
            return number + " is not prime, it is divisible by 2.";
        }
        for(int i =3; i<Math.floor(Math.sqrt(number)) + 1; i = i+2){
            if (number % i == 0){
                return number+ " is not prime, it is divisibel by " + i +".";
            }
        }
        if(number > highestPrimeNumberSoFar){
            highestPrimeNumberSoFar = number;
        }
        return number + " is prime. ";
    }

    @Gauge(name = "HighestPrimeNumberSoFar", description = "What is the highest number calculated so far", unit = MetricUnits.NONE)
    public Long getHighestPrimeNumber(){
        return highestPrimeNumberSoFar;
    }
}
