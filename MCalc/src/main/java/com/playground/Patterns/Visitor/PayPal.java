package com.playground.Patterns.Visitor;

import com.playground.Patterns.Visitor.api.PaymentMethod;
import com.playground.Patterns.Visitor.api.PaymentProcessor;

public class PayPal implements PaymentMethod {

    private String email;

    public PayPal(String email) {
        this.email = email;
    }

    @Override
    public String type() {
        return "PayPal (" + email + ")";
    }

    @Override
    public void processPayment(PaymentProcessor paymentProcessor) {
        paymentProcessor.doThings(this);
    }
}
