package com.umaxcode.microservice.inventory;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void addInventory_ShouldCreateInventory() {

        String inventoryCreationRequest = """
                {
                  "skuCode": "iphone_6",
                   "quantity": 34
                 }""";

        RestAssured.given()
                .contentType("application/json")
                .body(inventoryCreationRequest)
                .when()
                .post("/api/v1/inventories")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("skuCode", Matchers.is("iphone_6"))
                .body("quantity", Matchers.is(34));
    }

}
