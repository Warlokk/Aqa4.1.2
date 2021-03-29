package ru.netology;

import com.github.javafaker.Bool;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator.*;
import ru.netology.data.DeliveryInfo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.openqa.selenium.Keys.*;

public class DeliveryTest {
    private DeliveryRequest request = new DeliveryRequest();
    private DeliveryInfo client = request.clientInfo("ru");


    private LocalDate plusDays(int n) {
        LocalDate date = LocalDate.now();
        date = date.plusDays(n);
        return date;
    }


    @Test
    void shouldRescheduleAndSend() {
        open("http://localhost:9999");
        $$("[type=text]").first().setValue(client.getCity());
        $("[placeholder='Дата встречи']").sendKeys(CONTROL + "a", DELETE);
        String newDate = plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").setValue(newDate);
        $("[data-test-id=name] [type=text]").setValue(client.getName());
        $("[name=phone]").setValue(client.getPhone());
        $(".checkbox__box").click();
        $("div.form-field>[type=button]").submit();
        Boolean incorrectCity = $(withText("недоступна")).isDisplayed();
        if (incorrectCity) {
            $$("[type=text]").first().sendKeys(CONTROL + "a", DELETE);
            $$("[type=text]").first().setValue("Москва");
            $("div.form-field>[type=button]").submit();
        }
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));
        closeWindow();

        open("http://localhost:9999");
        if (incorrectCity) {
            $$("[type=text]").first().setValue("Москва");
        } else {
            $$("[type=text]").first().setValue(client.getCity());
        }
        $("[placeholder='Дата встречи']").sendKeys(CONTROL + "a", DELETE);
        newDate = plusDays(8).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").setValue(newDate);
        $("[data-test-id=name] [type=text]").setValue(client.getName());
        $("[name=phone]").setValue(client.getPhone());
        $(".checkbox__box").click();
        $("div.form-field>[type=button]").submit();
        $(withText("Необходимо")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content>[type=button]").click();
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));
    }


}
