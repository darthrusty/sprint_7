package ru.yandex.sprint7;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import ru.yandex.sprint7.pojo.GetOrderList;
import ru.yandex.sprint7.clients.OrderClient;
import java.util.concurrent.ThreadLocalRandom;

public class OrderListTest {

    private OrderClient orderClient = new OrderClient();
    private Integer courierId     = ThreadLocalRandom.current().nextInt(1, 10);
    private String nearestStation = RandomStringUtils.randomAlphabetic(8);
    private Integer limit         = ThreadLocalRandom.current().nextInt(1, 30);
    private Integer page          = ThreadLocalRandom.current().nextInt(1, 10);

    @Test
    @DisplayName("Проверка получения списка заказов")
    public void getOrderList() {

        GetOrderList getOrderList = new GetOrderList();
        getOrderList.setCourierId(courierId);
        getOrderList.setNearestStation(nearestStation);
        getOrderList.setLimit(limit);
        getOrderList.setPage(page);

        orderClient.getList(getOrderList)
                .statusCode(200)
                .body("orders", Matchers.not(Matchers.emptyArray()));
    }

}
