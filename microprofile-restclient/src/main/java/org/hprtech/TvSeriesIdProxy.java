package org.hprtech;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient(baseUri = "https://api.tvmaze.com")
public interface TvSeriesIdProxy {

    //https://api.tvmaze.com/shows/169
    @GET
    @Path("shows/{id}")
    TvSeries getTvSeriesById(@PathParam("id") int id);

    //https://api.tvmaze.com/singlesearch/shows?q=girls
    @GET
    @Path("/singlesearch/shows")
    TvSeries getTvSeriesByName(@QueryParam("q") String name);
}
