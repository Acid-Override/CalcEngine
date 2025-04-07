package com.playground.Patterns.Visitor;

import com.playground.Patterns.Visitor.api.PaymentMethod;
import com.playground.Patterns.Visitor.api.PaymentProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodTest {

    @Test
    void type() {
        String cashPaymentMethod = describePaymentMethod(new Cash());
        assertEquals("cash", cashPaymentMethod);
        String creditCardPaymentMethod = describePaymentMethod(new CreditCard("1234"));
        assertEquals("CreditCard (1234)", creditCardPaymentMethod);
        String payPalPaymentMethod = describePaymentMethod(new PayPal("p@p.com"));
        assertEquals("PayPal (p@p.com)", payPalPaymentMethod);
    }

    private String describePaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethod.type();
    }

    @Test
    void processPaymentCash() {
        PaymentMethod cashPaymentMethod = new Cash();
        cashPaymentMethod.processPayment(new LogPayment());
        cashPaymentMethod.processPayment(new FinalizePayment());

        assertEquals("cash", cashPaymentMethod.type());
    }

    @Test
    void processPaymentCreditCard() {
        PaymentMethod creditCardPaymentMethod = new CreditCard("1234");
        creditCardPaymentMethod.processPayment(new LogPayment());
        creditCardPaymentMethod.processPayment(new FinalizePayment());

        assertEquals("CreditCard (1234)", creditCardPaymentMethod.type());
    }

    @Test
    void processPaymentPayPal() {
        PaymentMethod payPalPaymentMethod = new PayPal("p@p.com");
        payPalPaymentMethod.processPayment(new LogPayment());
        payPalPaymentMethod.processPayment(new FinalizePayment());

        assertEquals("PayPal (p@p.com)", payPalPaymentMethod.type());
    }
}