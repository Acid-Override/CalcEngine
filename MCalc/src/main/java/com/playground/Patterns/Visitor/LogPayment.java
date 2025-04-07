package com.playground.Patterns.Visitor;

import com.playground.Patterns.Visitor.api.PaymentProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogPayment implements PaymentProcessor {
    @Override
    public void doThings(Cash paymentMethod) {
        log.info("Processing Cash Payment");
    }

    @Override
    public void doThings(PayPal paymentMethod) {
        log.info("Processing PayPal Payment");
    }

    @Override
    public void doThings(CreditCard paymentMethod) {
        log.info("Processing Credit Card Payment");
    }
}
