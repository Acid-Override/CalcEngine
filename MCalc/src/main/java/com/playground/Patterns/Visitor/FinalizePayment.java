package com.playground.Patterns.Visitor;

import com.playground.Patterns.Visitor.api.PaymentProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FinalizePayment implements PaymentProcessor {
    @Override
    public void doThings(Cash paymentMethod) {
        // Cash Things
        log.info("Finalizing Cash Payment");
    }

    @Override
    public void doThings(PayPal paymentMethod) {
        // PayPal Things
        log.info("Finalizing PayPal Payment");
    }

    @Override
    public void doThings(CreditCard paymentMethod) {
        // CreditCard Things
        log.info("Finalizing CreditCard Payment");
    }
}
