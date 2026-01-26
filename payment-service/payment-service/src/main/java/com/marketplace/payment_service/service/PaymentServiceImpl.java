package com.marketplace.payment_service.service;

import com.marketplace.payment_service.dto.PaymentRequest;
import com.marketplace.payment_service.dto.PaymentResponse;
import com.marketplace.payment_service.entity.Payment;
import com.marketplace.payment_service.enums.Currency;
import com.marketplace.payment_service.enums.Status;
import com.marketplace.payment_service.exception.PaymentAlreadyPresentException;
import com.marketplace.payment_service.exception.PaymentFailureException;
import com.marketplace.payment_service.exception.PaymentProcessingException;
import com.marketplace.payment_service.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.nio.file.ProviderNotFoundException;
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
            Payment payment = paymentRepository.findByOrderId(paymentRequest.orderId());
            if (payment.getStatus()==Status.PROCESSING){
                throw new PaymentProcessingException("Cannot complete request as payment is being processed ");
            }
            if (payment.getStatus()==Status.COMPLETED){
                throw new PaymentAlreadyPresentException("Payment already received");
            }
        }

// new payment creation
        UUID id = UUID.randomUUID();
        Payment newPayment = getNewPayment(paymentRequest, id);
        paymentRepository.save(newPayment);

     return mapToResponseFromEntity(newPayment);
    }



    private PaymentResponse processPayment(PaymentResponse paymentResponse){

        Payment newPayment = paymentRepository.findByPaymentId(paymentResponse.paymentId());

        // calling payment provider
        Status status = paymentProviderTesterPass(newPayment.getCurrency(),newPayment.getAmount(),newPayment.getPaymentId());
        newPayment.setStatus(status);

        return mapToResponseFromEntity(newPayment);
    }

    private PaymentResponse retryPayment(PaymentResponse paymentResponse){
        Payment newPayment = paymentRepository.findByPaymentId(paymentResponse.paymentId());
     return mapToResponseFromEntity(   retryPayment(newPayment));

    }







    private  Payment getNewPayment(PaymentRequest paymentRequest, UUID id) {
        Payment newPayment = new Payment();
        newPayment.setStatus(Status.PROCESSING);
        newPayment.setPaymentId(id);
        newPayment.setProviderPaymentId(paymentRequest.providerPaymentId());
        newPayment.setProvider(paymentRequest.provider());
        newPayment.setAmount(paymentRequest.amount());
        newPayment.setCurrency(paymentRequest.currency());
        newPayment.setOrderId(paymentRequest.orderId());
        newPayment.setMethod(paymentRequest.method());
        newPayment.setOrderReference(paymentRequest.orderReference());
        newPayment.setRetryCount(0);
        return newPayment;
    }
    private Status paymentProviderTesterPass(Currency currency,
                                          BigDecimal amount ,
                                          UUID paymentId){
        return Status.COMPLETED;

    }

    private Status paymentProviderTesterFail(Currency currency,
                                             BigDecimal amount ,
                                             UUID paymentId){
        return Status.Failed;

    }








    private Payment retryPayment(Payment payments) {
Payment payment = paymentRepository.findByPaymentId(payments.getPaymentId());

        if (payment.getStatus() == Status.Failed) {
            Integer retryCount = payment.getRetryCount();
            if (retryCount >= 3) {
                throw new PaymentFailureException("Payment retry limit exceeded : cannot complete payment");
            }
          Status status =  paymentProviderTesterPass(payment.getCurrency(),payment.getAmount(),payment.getPaymentId());
            payment.setStatus(status);
        }
        return payment;

    }


    @Override
    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(()->new ProviderNotFoundException("No payment Found with the id "+id));
        return mapToResponseFromEntity(payment);
    }

    @Override
    public PaymentResponse getPaymentByPaymentId(UUID paymentId) {
        Payment payment = paymentRepository.findByPaymentId(paymentId);


        return mapToResponseFromEntity(payment);
    }

    @Override
    public List<PaymentResponse> getPaymentByStatus(Status status) {
        List<Payment>payments = paymentRepository.findAllByStatus(status);

        return payments.stream().map(this::mapToResponseFromEntity).toList();
    }

    @Override
    public List<PaymentResponse> getPaymentByProvider(String providerPaymentId) {
        List<Payment>payments = paymentRepository.findByProviderPaymentId(providerPaymentId);

        return payments.stream().map(this::mapToResponseFromEntity).toList();

    }


private PaymentResponse mapToResponseFromEntity(Payment payment){
        PaymentResponse response = new PaymentResponse(payment.getId(),payment.getPaymentId(), payment.getOrderReference(), payment.getOrderId(), payment.getAmount(),payment.getStatus(),payment.getMethod(), payment.getProvider(), payment.getProviderPaymentId());
   return response;

}


}
