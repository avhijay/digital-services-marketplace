package com.marketplace.payment_service.service;

import com.marketplace.payment_service.dto.PaymentRequest;
import com.marketplace.payment_service.dto.PaymentResponse;
import com.marketplace.payment_service.entity.Payment;
import com.marketplace.payment_service.enums.Status;
import com.marketplace.payment_service.exception.PaymentAlreadyPresentException;
import com.marketplace.payment_service.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class PaymentServiceImpl implements  PaymentService{

    private final PaymentRepository paymentRepository;
    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    public PaymentServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository=paymentRepository;
    }



    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {




        // checking if payment already exist

        if (paymentRepository.findByOrderId(paymentRequest.orderId())!=null ){
            throw  new PaymentAlreadyPresentException("Current payment for the order already exist");
        }


        UUID id = UUID.randomUUID();
        Payment newPayment = new Payment();
        newPayment.setPaymentId(id);
        newPayment.setProviderPaymentId(paymentRequest.providerPaymentId());
        newPayment.setProvider(paymentRequest.provider());
        newPayment.setAmount(paymentRequest.amount());
        newPayment.setCurrency(paymentRequest.currency());
        newPayment.setOrderId(paymentRequest.orderId());
        newPayment.setMethod(paymentRequest.method());
        newPayment.setOrderReference(paymentRequest.orderReference());
        newPayment.setRetryCount(0);
        newPayment.setStatus(Status.PROCESSING);


    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        return null;
    }

    @Override
    public PaymentResponse getPaymentByPaymentId(UUID paymentId) {
        return null;
    }

    @Override
    public List<PaymentResponse> getPaymentByStatus(Status status) {
        return List.of();
    }

    @Override
    public List<PaymentResponse> getPaymentByProvider(String ProviderPaymentId) {
        return List.of();
    }




}
