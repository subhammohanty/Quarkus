package org.hprtech.restclient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.hprtech.dto.Post;

import java.util.List;

@Path("")
@RegisterRestClient(baseUri = "https://jsonplaceholder.typicode.com")
public interface JsonPlaceHolderRestClient {

    @GET
    @Path("/posts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getAllPosts();
}
