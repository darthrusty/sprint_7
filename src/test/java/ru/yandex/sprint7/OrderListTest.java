package ru.yandex.sprint7;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.sprint7.pojo.GetOrderList;
import ru.yandex.sprint7.clients.OrderClient;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListTest {

    private OrderClient orderClient = new OrderClient();
    private Integer courierId = 5;
    private String nearestStation = "1";
    private Integer limit = 30;
    private Integer page = 0;

    @Test
    @DisplayName("Проверка получения списка заказов")
    public void GetOrderList() {

        GetOrderList getOrderList = new GetOrderList();
        getOrderList.setCourierId(courierId);
        getOrderList.setNearestStation(nearestStation);
        getOrderList.setLimit(limit);
        getOrderList.setPage(page);

        orderClient.getList(getOrderList)
                .statusCode(200)
                .body("orders", notNullValue());
    }

}
