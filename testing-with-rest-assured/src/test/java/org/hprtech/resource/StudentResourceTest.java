package org.hprtech.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;


import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentResourceTest {

    @Order(1)
    @Test
    void saveStudent() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("studentId", 10L)
                .add("name", "Elon Musk")
                .add("branch", "CSE")
                .build();

        RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post("saveStudent")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());
    }
    @Order(2)
    @Test
    void getStudentById() {
        RestAssured
                .given()
                .when()
                .get("student/10")
                .then()
                .body("name", equalTo("Elon Musk"))
                .body("branch", equalTo("CSE"));
    }
    @Order(3)
    @Test
    void getStudentList() {
        RestAssured
                .given()
                .when()
                .get("getAllStudent")
                .then()
                .body("size()", equalTo(6));
    }

//    @Test
//    void getStudentList() {
//        RestAssured
//                .given()
//                .when()
//                .get("/getAllStudent")
//                .then()
//                .body("size()", equalTo(4))
//                .body("branch", hasItems("CSE","ME"))
//                .body("branch", hasItems("CSE","ME"));
//
//    }
//
//    @Test
//    void getCSEStudents() {
//        RestAssured
//                .given()
//                .when()
//                .get("/getAllCSEStudent")
//                .then()
//                .body("size()", equalTo(2))
//                .body("name", hasItems("Subham","Ansuman"));
//    }
}