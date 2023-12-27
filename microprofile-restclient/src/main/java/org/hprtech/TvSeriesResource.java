package org.hprtech;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.temporal.ChronoUnit;

@Path("/tvseries")
public class TvSeriesResource {

    @RestClient
    TvSeriesIdProxy proxy;

    @GET
    @Path("/{id}")
    @Fallback(fallbackMethod = "getTvSeriesByIdFallBack")
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5, delay = 10, delayUnit = ChronoUnit.SECONDS)
    //Here from 4 requests it will check how many has failed (without exception) , if it is greater than 0.5. Then it waits for 10 sec then again hits
    @Retry(maxRetries = 3)
    // To let the application know how many times it needs to retry after getting error.
    // After 3 attempts fallback method will be called
    @Timeout(1000)
    //This annotation indicates that if we don't get the response in 1sec the call the fallback method.
    //As we added retry also , so first retry will be called then it will wait for 1 sec then fallback method will be called
    public Response getTvSeriesById(@PathParam("id") int id){
        return Response.ok(proxy.getTvSeriesById(id)).build();
    }

    public Response getTvSeriesByIdFallBack(int id){
        return Response.ok("Site Is Under Maintenance!!").build();
    }

    @GET
    @Path("/person/{personName}")
    public TvSeries getTvSeriesByName(@PathParam("personName") String personName){
        return proxy.getTvSeriesByName(personName);
    }
}
