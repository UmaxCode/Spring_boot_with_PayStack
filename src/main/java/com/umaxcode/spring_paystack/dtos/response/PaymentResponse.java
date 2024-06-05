package com.umaxcode.spring_paystack.dtos.response;

import java.util.Map;

public record PaymentResponse(String status, String message, Map<String, Object> data) {
}
