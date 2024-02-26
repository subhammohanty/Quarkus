package org.hprtech.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.hprtech.restclient.JsonPlaceHolderRestClient;

@Liveness
public class LivenessHealthCheck implements HealthCheck {

    @RestClient
    private JsonPlaceHolderRestClient jsonPlaceHolderRestClient;

    @Override
    public HealthCheckResponse call() {
        jsonPlaceHolderRestClient.getAllPosts();
        return HealthCheckResponse.named("JSONPlaceHolder APIs Health")
                .up()
                .build();
    }
}
