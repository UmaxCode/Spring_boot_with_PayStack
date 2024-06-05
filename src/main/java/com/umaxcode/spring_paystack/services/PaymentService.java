package com.umaxcode.spring_paystack.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.umaxcode.spring_paystack.dtos.request.InitiatePaymentRequest;
import com.umaxcode.spring_paystack.dtos.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Value("${application.paystack.initialization.url}")
    private String paymentInitiationURL;

    @Value("${application.paystack.verification.url}")
    private String paymentVerificationURL;

    @Value("${application.paystack.secret.key}")
    private String payStackPaymentSecretKey;


   public PaymentResponse initiatePayment(InitiatePaymentRequest request) throws IOException {

       try (CloseableHttpClient client = HttpClients.createDefault()) {

           HttpPost postRequest = new HttpPost(paymentInitiationURL);

           postRequest.setHeader("Content-Type", "application/json");
           postRequest.setHeader("Authorization", "Bearer " + payStackPaymentSecretKey);

           Map<String, Object> data =  new HashMap<>();

           data.put("email", request.email());
           data.put("amount", request.amount());

           StringEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(data));
           postRequest.setEntity(entity);

           String payStackResponseBody = EntityUtils.toString(client.execute(postRequest).getEntity());

           Gson gson = new Gson();

           return gson.fromJson(payStackResponseBody, PaymentResponse.class);

       }

   }

   public PaymentResponse verifyPayment( String reference) throws IOException {

       try(CloseableHttpClient client = HttpClients.createDefault()){

           HttpGet getRequest = new HttpGet(paymentVerificationURL+"/"+reference);

           getRequest.setHeader("Authorization", "Bearer " + payStackPaymentSecretKey);

           String payStackResponseBody = EntityUtils.toString(client.execute(getRequest).getEntity());

           Gson gson = new Gson();

           return gson.fromJson(payStackResponseBody, PaymentResponse.class);

       }

   }
}
