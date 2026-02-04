package com.marketplace.payment_service.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentAsyncService {

    private  final PaymentService paymentService;

    public PaymentAsyncService(PaymentService paymentService){
        this.paymentService = paymentService;
    }


    @Async
    public void processPayment(UUID paymentId){
        paymentService.processPayment(paymentId);
    }

}
