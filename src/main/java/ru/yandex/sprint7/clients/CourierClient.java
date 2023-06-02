package ru.yandex.sprint7.clients;

import io.restassured.response.ValidatableResponse;
import ru.yandex.sprint7.pojo.CreateCourier;
import ru.yandex.sprint7.pojo.LoginCourier;

import static io.restassured.RestAssured.given;

public class CourierClient extends BaseClient{

    private static final String COURIER_API_URL       = "api/v1/courier";
    private static final String COURIER_LOGIN_API_URL = "api/v1/courier/login";

    public ValidatableResponse create(CreateCourier createCourier) {
        return given()
                    .spec(getSpec())
                    .body(createCourier)
                    .when()
                    .post(COURIER_API_URL)
                    .then();
    }

    public ValidatableResponse login(LoginCourier loginCourier) {
        return given()
                    .spec(getSpec())
                    .body(loginCourier)
                    .when()
                    .post(COURIER_LOGIN_API_URL)
                    .then();
    }

    public ValidatableResponse delete(Integer id) {
        return given()
                    .spec(getSpec())
                    .pathParams("id", id)
                    .when()
                    .delete(COURIER_API_URL + "/{id}")
                    .then();
    }

}
