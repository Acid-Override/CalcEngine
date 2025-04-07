package com.playground.Patterns.Visitor.api;

public interface PaymentMethod {
    String type();
    void processPayment(PaymentProcessor paymentProcessor);
}
