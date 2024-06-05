package com.umaxcode.spring_paystack.dtos.request;

public record InitiatePaymentRequest(String email, int amount) {
}
