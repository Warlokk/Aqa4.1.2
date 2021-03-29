package ru.netology.data;

import com.github.javafaker.Address;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.PhoneNumber;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class DeliveryInfo {
    private final String name;
    private final String address;
    private final String phone;

}
