package com.umaxcode.microservices.order;

import com.umaxcode.microservices.order.domain.dto.OrderRequestDTO;
import com.umaxcode.microservices.order.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
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

        OrderRequestDTO request = OrderRequestDTO.builder()
                .skuCode("iphone_6")
                .price(BigDecimal.valueOf(1000))
                .quantity(1)
                .build();

        InventoryClientStub.stubInventoryCall(request.skuCode(), request.quantity());

        RestAssured.given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/orders")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("skuCode", Matchers.is("iphone_6"))
                .body("price", Matchers.greaterThanOrEqualTo(1000))
                .body("quantity", Matchers.equalTo(1));
    }

}
