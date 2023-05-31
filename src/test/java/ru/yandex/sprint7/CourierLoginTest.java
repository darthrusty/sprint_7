package ru.yandex.sprint7;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.sprint7.clients.CourierClient;
import ru.yandex.sprint7.pojo.CreateCourier;
import ru.yandex.sprint7.pojo.LoginCourier;
import static org.hamcrest.CoreMatchers.notNullValue;
public class CourierLoginTest {

    private CourierClient courierClient = new CourierClient();
    private String login     = "ninjazz007";
    private String password  = "1234";

    @Before
    public void CreateCourier() {

        CreateCourier createCourier = new CreateCourier();
        createCourier.setLogin(login);
        createCourier.setFirstName("firstName");
        createCourier.setPassword(password);

        courierClient.create(createCourier)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));
    }

    @Test
    @DisplayName("Удачный логин курьера")
    public void LoginCourier() {
        LoginCourier loginCourier = new LoginCourier();
        loginCourier.setLogin(login);
        loginCourier.setPassword(password);

        courierClient.login(loginCourier)
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("неудачный логин курьера без логина")
    public void LoginCourierWithoutLogin() {
        LoginCourier loginCourier = new LoginCourier();
        loginCourier.setPassword(password);

        courierClient.login(loginCourier)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("неудачный логин курьера без пароля")
    public void LoginCourierWithoutPassword() {
        LoginCourier loginCourier = new LoginCourier();
        loginCourier.setLogin(login);

        courierClient.login(loginCourier)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("неудачный логин курьера с неправильным логином")
    public void LoginCourierWithWrongLogin() {
        LoginCourier loginCourier = new LoginCourier();
        loginCourier.setLogin(login + "a");
        loginCourier.setPassword(password);

        courierClient.login(loginCourier)
                .statusCode(404);
    }

    @Test
    @DisplayName("неудачный логин курьера с неправильным паролем")
    public void LoginCourierWithWrongPassword() {
        LoginCourier loginCourier = new LoginCourier();
        loginCourier.setLogin(login);
        loginCourier.setPassword(password + "a");

        courierClient.login(loginCourier)
                .statusCode(404);
    }

    @Test
    @DisplayName("неудачный логин курьера с несуществующим логином")
    public void LoginNonexistentCourier() {
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

        courierClient.login(loginCourier)
                .statusCode(404)
                .body("message", Matchers.equalTo("Учетная запись не найдена"));
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
