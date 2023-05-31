package ru.yandex.sprint7;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.sprint7.clients.OrderClient;
import ru.yandex.sprint7.pojo.CreateOrder;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest {

    private OrderClient orderClient = new OrderClient();

    private String firstName = "Naruto";
    private String lastName = "Uchiha";
    private String address = "Konoha, 142 apt.";
    private String metroStation = "4";
    private String phone = "+7 800 355 35 35";
    private int rentTime = 5;
    private String deliveryDate = "2023-06-06";
    private String comment = "Saske, come back to Konoha";
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
    public void OrderCreated() {

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
