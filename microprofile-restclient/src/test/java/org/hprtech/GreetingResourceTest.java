package org.hprtech;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @RestClient
    TvSeriesIdProxy proxy;

//    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

    @Test
    void test2(){
        String name = this.proxy.getClass().getName();
        String packageName = this.proxy.getClass().getPackageName();
        System.out.println(name);
        System.out.println(packageName);
    }

}