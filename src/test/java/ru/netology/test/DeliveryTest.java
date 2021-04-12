package ru.netology.test;


import org.junit.jupiter.api.*;

import ru.netology.data.DataGenerator;
import ru.netology.data.DataGenerator.*;
import ru.netology.data.DeliveryInfo;


import java.time.*;



import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.openqa.selenium.Keys.*;

public class DeliveryTest {
    private DeliveryRequest request = new DeliveryRequest();
    private DeliveryInfo client = request.clientInfo("ru");


    @Test
    void shouldRescheduleAndSend() {
        open("http://localhost:9999");
        $$("[type=text]").first().setValue(client.getCity());
        $("[placeholder='Дата встречи']").sendKeys(CONTROL + "a", DELETE);
        String newDate = DataGenerator.plusDays(4);
        $("[placeholder='Дата встречи']").setValue(newDate);
        $("[data-test-id=name] [type=text]").setValue(client.getName());
        $("[name=phone]").setValue(client.getPhone());
        $(".checkbox__box").click();
        $("div.form-field>[type=button]").submit();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно запланирована на " + newDate));
        closeWindow();

        open("http://localhost:9999");
        $$("[type=text]").first().setValue(client.getCity());
        $("[placeholder='Дата встречи']").sendKeys(CONTROL + "a", DELETE);
        newDate = DataGenerator.plusDays(8);
        $("[placeholder='Дата встречи']").setValue(newDate);
        $("[data-test-id=name] [type=text]").setValue(client.getName());
        $("[name=phone]").setValue(client.getPhone());
        $(".checkbox__box").click();
        $("div.form-field>[type=button]").submit();
        $(withText("Необходимо")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content>[type=button]").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно запланирована на " + newDate));

    }


}
