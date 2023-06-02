package ru.yandex.sprint7;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.sprint7.clients.OrderClient;
import ru.yandex.sprint7.pojo.CreateOrder;
import java.util.concurrent.ThreadLocalRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest {

    private OrderClient orderClient = new OrderClient();

    private String firstName    = RandomStringUtils.randomAlphabetic(8);
    private String lastName     = RandomStringUtils.randomAlphabetic(8);
    private String address      = RandomStringUtils.randomAlphabetic(8);
    private String metroStation = RandomStringUtils.randomAlphabetic(2);
    private String phone        = RandomStringUtils.randomAlphabetic(12);
    private int rentTime        = ThreadLocalRandom.current().nextInt(1, 30);
    private String deliveryDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    private String comment      = RandomStringUtils.randomAlphabetic(33);
    private String[] color;

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {null},
        };
    }

    public OrderCreateTest(String[] color) {
        this.color = color;
    }

    @Test
    @DisplayName("Проверка создания заказа")
    public void orderCreated() {

        CreateOrder createOrder = new CreateOrder();
        createOrder.setFirstName(firstName);
        createOrder.setLastName(lastName);
        createOrder.setAddress(address);
        createOrder.setMetroStation(metroStation);
        createOrder.setPhone(phone);
        createOrder.setRentTime(rentTime);
        createOrder.setDeliveryDate(deliveryDate);
        createOrder.setComment(comment);
        createOrder.setColor(color);

        orderClient.create(createOrder)
                .statusCode(201)
                .body("track", notNullValue());

    }

}
