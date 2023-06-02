package ru.yandex.sprint7;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import ru.yandex.sprint7.clients.CourierClient;
import ru.yandex.sprint7.pojo.CreateCourier;
import ru.yandex.sprint7.pojo.LoginCourier;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierCreateTest {

    private CourierClient courierClient = new CourierClient();
    private String login     = RandomStringUtils.randomAlphabetic(8);
    private String password  = RandomStringUtils.randomAlphabetic(8);
    private String firstName = RandomStringUtils.randomAlphabetic(8);

    @Test
    @DisplayName("Успешное создание курьера")
    public void createCourier() {

        CreateCourier createCourier = new CreateCourier();
        createCourier.setLogin(login);
        createCourier.setFirstName(firstName);
        createCourier.setPassword(password);

        courierClient.create(createCourier)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));
    }

    @Test
    @DisplayName("неуспешное создание курьера с аналогичными параметрами")
    public void createTwinCourier() {

        CreateCourier createCourier = new CreateCourier();
        createCourier.setLogin(login);
        createCourier.setFirstName(firstName);
        createCourier.setPassword(password);

        courierClient.create(createCourier)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));
        courierClient.create(createCourier)
                .statusCode(409)
                .body("message", Matchers.equalTo("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Неуспешное создание курьера без пароля")
    public void createCourierWithoutPassword() {
        CreateCourier createCourier = new CreateCourier();
        createCourier.setLogin(login);
        createCourier.setFirstName(firstName);

        courierClient.create(createCourier)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Неуспешное создание курьера без логина")
    public void createCourierWithoutLogin() {
        CreateCourier createCourier = new CreateCourier();
        createCourier.setFirstName(firstName);
        createCourier.setPassword(password);

        courierClient.create(createCourier)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Неуспешное создание курьера без фамилии")
    public void createCourierWithoutFirstName() {
        CreateCourier createCourier = new CreateCourier();
        createCourier.setLogin(login);
        createCourier.setPassword(password);

        courierClient.create(createCourier)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void tearDown() {
        Integer id;
        LoginCourier loginCourier = new LoginCourier();
        loginCourier.setLogin(login);
        loginCourier.setPassword(password);

        id = courierClient.login(loginCourier)
                .extract().jsonPath().get("id");

        if (id != null) {
            courierClient.delete(id)
                    .statusCode(200)
                    .body("ok", Matchers.equalTo(true));
        }
    }

}
