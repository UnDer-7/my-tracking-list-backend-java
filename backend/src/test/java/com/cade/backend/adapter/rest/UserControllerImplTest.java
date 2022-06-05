package com.cade.backend.adapter.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class UserControllerImplTest {

    @Test
    void testEndpoint() {
        given()
            .when().get("/hello")
            .then()
            .statusCode(RestResponse.StatusCode.OK)
            .body("$", Matchers.hasKey("email"));
    }
}
