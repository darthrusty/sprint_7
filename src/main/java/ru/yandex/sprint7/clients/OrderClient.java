package ru.yandex.sprint7.clients;

import io.restassured.response.ValidatableResponse;
import ru.yandex.sprint7.pojo.CreateOrder;
import ru.yandex.sprint7.pojo.GetOrderList;

import static io.restassured.RestAssured.given;

public class OrderClient extends BaseClient{

    public ValidatableResponse create(CreateOrder сreateOrder) {
        return given()
                .spec(getSpec())
                .body(сreateOrder)
                .when()
                .post("api/v1/orders")
                .then();
    }

    public ValidatableResponse getList(GetOrderList getOrderList) {
        return given()
                .spec(getSpec())
                .body(getOrderList)
                .when()
                .get("api/v1/orders")
                .then();
    }

}
