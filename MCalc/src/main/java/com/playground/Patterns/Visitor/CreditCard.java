package com.playground.Patterns.Visitor;

import com.playground.Patterns.Visitor.api.PaymentMethod;
import com.playground.Patterns.Visitor.api.PaymentProcessor;

public class CreditCard implements PaymentMethod {

    private final String number;

    public CreditCard(String number) {
        this.number = number;
    }

    @Override
    public String type() {
        return "CreditCard (" + number + ")";
    }

    @Override
    public void processPayment(PaymentProcessor paymentProcessor) {
        paymentProcessor.doThings(this);
    }
}
