package ru.yandex.sprint7.clients;

import io.restassured.response.ValidatableResponse;
import ru.yandex.sprint7.pojo.CreateCourier;
import ru.yandex.sprint7.pojo.LoginCourier;

import static io.restassured.RestAssured.given;

public class CourierClient extends BaseClient{

    public ValidatableResponse create(CreateCourier createCourier) {
        return given()
                    .spec(getSpec())
                    .body(createCourier)
                    .when()
                    .post("api/v1/courier")
                    .then();
    }

    public ValidatableResponse login(LoginCourier loginCourier) {
        return given()
                    .spec(getSpec())
                    .body(loginCourier)
                    .when()
                    .post("api/v1/courier/login")
                    .then();
    }

    public ValidatableResponse delete(Integer id) {
        return given()
                   .spec(getSpec())
                    .pathParams("id", id)
                    .when()
                    .delete("api/v1/courier/{id}")
                    .then();
    }

}
