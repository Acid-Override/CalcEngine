package com.playground.Patterns.Visitor.api;

import com.playground.Patterns.Visitor.Cash;
import com.playground.Patterns.Visitor.CreditCard;
import com.playground.Patterns.Visitor.PayPal;

public interface PaymentProcessor {
    void doThings(Cash paymentMethod);
    void doThings(PayPal paymentMethod);
    void doThings(CreditCard paymentMethod);
}
