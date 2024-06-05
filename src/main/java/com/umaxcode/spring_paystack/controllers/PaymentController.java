package com.umaxcode.spring_paystack.controllers;

import com.umaxcode.spring_paystack.services.PaymentService;
import com.umaxcode.spring_paystack.dtos.request.InitiatePaymentRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/initialize")
    public ResponseEntity<?> initialize(@RequestBody InitiatePaymentRequest request) throws IOException {

        var response = paymentService.initiatePayment(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/verify/{reference}")
    public ResponseEntity<?> verify(@PathVariable String reference) throws IOException {

        var response = paymentService.verifyPayment(reference);
        return ResponseEntity.ok(response);
    }


    @ExceptionHandler
    public ResponseEntity<?> paymentExceptionHandler(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
