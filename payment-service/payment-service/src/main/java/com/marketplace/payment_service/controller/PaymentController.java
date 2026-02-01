package com.marketplace.payment_service.controller;

import com.marketplace.payment_service.dto.PaymentRequest;
import com.marketplace.payment_service.dto.PaymentResponse;
import com.marketplace.payment_service.enums.Status;
import com.marketplace.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    public PaymentController (PaymentService paymentService) {
        this.paymentService=paymentService;

    }

    @PostMapping
    public ResponseEntity<PaymentResponse>createPayment( @RequestHeader("X-Idempotency-Key") String idempotencyKey ,
                                                         @Valid @RequestBody PaymentRequest paymentRequest){
        log.info("Request : create payment - received ");
        PaymentResponse response=  paymentService.createPayment(paymentRequest ,idempotencyKey);

        URI location = URI.create("/payments/"+response.paymentId());//to decide-------!!
        return ResponseEntity.created(location).body(response);

    }

    @PostMapping("/{paymentId}/process")
    public ResponseEntity<Void> processPayment(@PathVariable UUID paymentId){
        paymentService.processPayment(paymentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{paymentId}/retry")
    public ResponseEntity<PaymentResponse>retryPayment(@PathVariable UUID paymentId) {
        PaymentResponse response = paymentService.retryPayment(paymentId);
        return ResponseEntity.ok(response);

    }




        @GetMapping("/paymentId/{id}")
    public  ResponseEntity<PaymentResponse>getPaymentByPaymentId(@PathVariable UUID paymentId){
        PaymentResponse response = paymentService.getPaymentByPaymentId(paymentId);

        return ResponseEntity.ok(response);
        }

        @GetMapping("/status/{status}")
        public  ResponseEntity<List< PaymentResponse>>getPaymentByStatus(@PathVariable Status status){
       List <PaymentResponse> responses = paymentService.getPaymentByStatus(status);
       return ResponseEntity.ok(responses);
        }



    @GetMapping("/provider/{providerId}")
    public  ResponseEntity<List< PaymentResponse>>getPaymentByProvider(@PathVariable String providerId){
        List <PaymentResponse> responses = paymentService.getPaymentByProvider(providerId);
        return ResponseEntity.ok(responses);
    }
















}
