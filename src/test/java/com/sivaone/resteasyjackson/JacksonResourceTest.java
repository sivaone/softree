package com.sivaone.resteasyjackson;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
class JacksonResourceTest {

    @Test
    void testListQuarksEndpoint() {
        given()
                .when().get("/quarks")
                .then()
                .statusCode(200)
                .header("Content-type", "application/json")
                .body(containsStringIgnoringCase("quark"));
    }

    @Test
    void testAddQuarksEndpoint() {
        given()
                .request().body("{\"name\":\"Greet\", \"description\":\"Hello there! How are you?\"}")
                .header("Content-type", "application/json")
                .when().post("/quarks")
                .then()
                .statusCode(200)
                .header("Content-type", "application/json")
                .body(containsStringIgnoringCase("Hello there"));
    }

    @Test
    void testDeleteQuarksEndpoint() {
        given()
                .request().body("{\"name\":\"Up\", \"description\":\"The up quark\"}")
                .header("Content-type", "application/json")
                .when().delete("/quarks")
                .then()
                .statusCode(200)
                .header("Content-type", "application/json")
                .body(not(containsStringIgnoringCase("The up quark")));
    }
}