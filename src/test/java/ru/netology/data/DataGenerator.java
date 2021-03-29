package ru.netology.data;

import com.github.javafaker.Faker;


import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class DeliveryRequest {
        private DeliveryRequest() {
        }

        public static DeliveryInfo clientInfo(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new DeliveryInfo(
                    faker.name().fullName(),
                    faker.address().city(),
                    faker.phoneNumber().phoneNumber()
            );
        }
    }
}