package ru.yandex.sprint7.clients;

import io.restassured.response.ValidatableResponse;
import ru.yandex.sprint7.pojo.CreateOrder;
import ru.yandex.sprint7.pojo.GetOrderList;

import static io.restassured.RestAssured.given;

public class OrderClient extends BaseClient{

    private static final String ORDER_API_URL = "api/v1/orders";

    public ValidatableResponse create(CreateOrder сreateOrder) {
        return given()
                .spec(getSpec())
                .body(сreateOrder)
                .when()
                .post(ORDER_API_URL)
                .then();
    }

    public ValidatableResponse getList(GetOrderList getOrderList) {
        return given()
                .spec(getSpec())
                .body(getOrderList)
                .when()
                .get(ORDER_API_URL)
                .then();
    }

}
