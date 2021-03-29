package ru.netology.data;

import com.github.javafaker.Faker;


import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class DeliveryRequest {
        public DeliveryRequest() {
        }

        public static DeliveryInfo clientInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new DeliveryInfo(
                    faker.name().lastName().replace("ё", "е") + " " + faker.name().firstName().replace("ё", "е"),
                    faker.address().city(),
                    faker.phoneNumber().phoneNumber()
            );
        }
    }

}