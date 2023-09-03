package org.acme.criticafilme.rest;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.acme.criticafilme.rest.dto.CreateUserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
class UserResourceTest {


    @TestHTTPResource("/users")
    URL apiURL;
    @Test
    @DisplayName("Criar User com sucesso")
    public void createUserTest() {

        var user = new CreateUserRequest();
        user.setName("Wendel");
        user.setAge(24);

        var response =
                given()
                        .contentType(ContentType.JSON)
                        .body(user)
                        .when()
                        .post(apiURL)
                        .then()
                        .extract().response();

        assertEquals(201, response.statusCode());
        assertNotNull(response.jsonPath().getString("id"));

    }
}