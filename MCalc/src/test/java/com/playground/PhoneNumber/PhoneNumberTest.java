package com.playground.PhoneNumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PhoneNumberTest {

    @Test
    void convertPhoneNumber() {
        String phoneNumber = "12345678901";
        PhoneNumber phoneNumber1 = new PhoneNumber();
        String result = phoneNumber1.convertPhoneNumber(phoneNumber);
        Assertions.assertEquals("12345678901", result);
    }

    @Test
    void convertPhoneNumberWithInvalidPhoneNumber() {
        String phoneNumber = "123456789";
        PhoneNumber phoneNumber1 = new PhoneNumber();
        Assertions.assertThrows(IllegalArgumentException.class, () -> phoneNumber1.convertPhoneNumber(phoneNumber));
    }

    @Test
    void convertPhoneNubmerWithCharacters() {
        String phoneNumber = "1800EATSHIT";
        PhoneNumber phoneNumber1 = new PhoneNumber();
        String result = phoneNumber1.convertPhoneNumber(phoneNumber);
        Assertions.assertEquals("18003287448", result);
    }
}