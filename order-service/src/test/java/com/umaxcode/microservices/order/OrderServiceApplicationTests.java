package com.umaxcode.microservices.order;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void placeOrder_ShouldCreateOrder() {

        String submitOrderRequest = """
                {
                    "skuCode": "iphone_15",
                    "price": 1000,
                    "quantity": 1
                }
                """;
        RestAssured.given()
                .contentType("application/json")
                .body(submitOrderRequest)
                .when()
                .post("/api/v1/orders")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("skuCode", Matchers.is("iphone_15"))
                .body("price", Matchers.greaterThanOrEqualTo(1000))
                .body("quantity", Matchers.equalTo(1));

    }

}
