package com.playground.Patterns.Visitor;

import com.playground.Patterns.Visitor.api.PaymentMethod;
import com.playground.Patterns.Visitor.api.PaymentProcessor;

public class Cash implements PaymentMethod {
    @Override
    public String type() {
        return "cash";
    }

    @Override
    public void processPayment(PaymentProcessor paymentProcessor) {
        paymentProcessor.doThings(this);
    }
}
