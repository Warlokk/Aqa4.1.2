package ru.netology.data;

import com.github.javafaker.Faker;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String plusDays(int n) {
        LocalDate date = LocalDate.now();
        String newDate = date.plusDays(n).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return newDate;
    }

    public static String city() {
        String[] cities = {"Москва", "Кемерово", "Смоленск", "Тамбов", "Пенза", "Липецк", "Петрозаводск", "Астрахань", "Саратов", "Санкт-Петербург"};
        return cities[new Random().nextInt(cities.length)];

    }

    public static class DeliveryRequest {
        public DeliveryRequest() {
        }


        public static DeliveryInfo clientInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new DeliveryInfo(
                    faker.name().lastName().replace("ё", "е") + " " + faker.name().firstName().replace("ё", "е"),
                    city(),
                    faker.phoneNumber().phoneNumber()
            );
        }


    }

}