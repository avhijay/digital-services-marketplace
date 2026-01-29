package com.marketplace.payment_service.service;

import com.marketplace.payment_service.domain.ProcessPaymentObject;
import com.marketplace.payment_service.domain.ProviderResultObject;
import com.marketplace.payment_service.dto.PaymentRequest;
import com.marketplace.payment_service.dto.PaymentResponse;
import com.marketplace.payment_service.entity.Payment;
import com.marketplace.payment_service.enums.Currency;
import com.marketplace.payment_service.enums.FailureReasons;
import com.marketplace.payment_service.enums.ProviderStatus;
import com.marketplace.payment_service.enums.Status;
import com.marketplace.payment_service.exception.PaymentAlreadyPresentException;
import com.marketplace.payment_service.exception.PaymentFailureException;
import com.marketplace.payment_service.exception.PaymentProcessingException;
import com.marketplace.payment_service.provider.PaymentProvider;
import com.marketplace.payment_service.repository.PaymentRepository;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.nio.file.ProviderNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class PaymentServiceImpl implements  PaymentService{

    private final PaymentRepository paymentRepository;
    private final PaymentProvider paymentProvider;
    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    public PaymentServiceImpl(PaymentRepository paymentRepository , PaymentProvider paymentProvider){
        this.paymentRepository=paymentRepository;
        this.paymentProvider=paymentProvider;
    }



    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest , String key) {
        String idempotencyKey = key;



log.info("Request - createPayment : received by Payment-service");
        // checking if payment already exist
log.debug("Checking if payment with orderId={} already exist ",paymentRequest.orderId());
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
        log.info("Generating payment ");
        UUID id = UUID.randomUUID();
        Payment newPayment = getNewPayment(paymentRequest, id , idempotencyKey);

        // handling duplicate payments
        try {

            paymentRepository.save(newPayment);
            log.info("Request - createPayment : success");
            return mapToResponseFromEntity(newPayment);

        }catch (ConstraintViolationException e ){

            PaymentResponse paymentResponse = mapToResponseFromEntity(paymentRepository.findByIdempotencyKey(idempotencyKey));
            return paymentResponse;

        }
    }


@Override
    public   void processPayment(UUID paymentId){
        log.info("Request - Process payment : received ");

        log.debug("Checking if payment={} exist",paymentId);
        Payment newPayment = paymentRepository.findByPaymentId(paymentId);
    if (newPayment.getStatus()==Status.COMPLETED){ // TO CHANGE
        throw new PaymentFailureException("Payment status mismatch");
    }

    newPayment.setStatus(Status.PROCESSING);

    ProcessPaymentObject paymentObject = new ProcessPaymentObject(newPayment.getPaymentId(),
            newPayment.getProviderPaymentId(),newPayment.getCurrency(),newPayment.getAmount());

        // calling payment provider
    log.info("Processing payment : start ");

    ProviderResultObject resultObject = paymentProvider.processPaymentPass(paymentObject);

        log.info("Processing payment - completed ");
        if (resultObject.getProviderStatus()== ProviderStatus.FAILURE){
            newPayment.setStatus(Status.Failed);
            newPayment.setFailureReason(resultObject.getFailureReasons());
        }else {
            newPayment.setStatus(Status.COMPLETED);

            paymentRepository.save(newPayment);
            log.info("Request - Process payment : success");
        }
    }


//
//    private PaymentResponse retryPayment(PaymentResponse paymentResponse){
//        Payment newPayment = paymentRepository.findByPaymentId(paymentResponse.paymentId());
//     return mapToResponseFromEntity(   retryPayment(newPayment));
//
//    }

    public PaymentResponse  retryPayment(UUID paymentId) {
        log.info("Request - retry newPayment : received ");

        Payment newPayment = paymentRepository.findByPaymentId(paymentId);

        log.debug("Verifying newPayment status :{}", newPayment.getStatus());
        log.info("Retrying newPayment : start ");

        if (newPayment.getStatus() == Status.Failed) {
            Integer retryCount = newPayment.getRetryCount();



            log.info("Current retry count :{}",retryCount);

            if (retryCount >= 3) {
                newPayment.setFailureReason(FailureReasons.PROVIDER_500);

                log.warn("Retry attempt exceeded :{} | max retry : 3 ",retryCount);
                throw new PaymentFailureException("Payment retry limit exceeded : cannot complete newPayment");
            }

            log.info("Updating retry count");

          newPayment.setRetryCount(  newPayment.getRetryCount()+1);
            ProcessPaymentObject paymentObject = new ProcessPaymentObject(newPayment.getPaymentId(),
                    newPayment.getProviderPaymentId(),newPayment.getCurrency(),newPayment.getAmount());

            ProviderResultObject resultObject = paymentProvider.processPaymentPass(paymentObject);


            if (resultObject.getProviderStatus()== ProviderStatus.FAILURE){
                newPayment.setStatus(Status.Failed);
                newPayment.setFailureReason(resultObject.getFailureReasons());
            }else {
                newPayment.setStatus(Status.COMPLETED);
                log.info("Retrying newPayment : finished ");
                log.info("Request - retry newPayment : success ");
            }
        }
        return mapToResponseFromEntity(newPayment);

    }





    private  Payment getNewPayment(PaymentRequest paymentRequest, UUID id ,String idempotencyKey ) {
        Payment newPayment = new Payment();
        newPayment.setStatus(Status.CREATED);
        newPayment.setPaymentId(id);
        newPayment.setProviderPaymentId(paymentRequest.providerPaymentId());
        newPayment.setProvider(paymentRequest.provider());
        newPayment.setAmount(paymentRequest.amount());
        newPayment.setCurrency(paymentRequest.currency());
        newPayment.setOrderId(paymentRequest.orderId());
        newPayment.setMethod(paymentRequest.method());
        newPayment.setOrderReference(paymentRequest.orderReference());
        newPayment.setIdempotencyKey(idempotencyKey);
        newPayment.setRetryCount(0);
        return newPayment;
    }




    private Status paymentProviderTesterPass(Currency currency,
                                          BigDecimal amount ,
                                          UUID paymentId){
        log.info("Payment success");

        return Status.COMPLETED;

    }

    private Status paymentProviderTesterFail(Currency currency,
                                             BigDecimal amount ,
                                             UUID paymentId){

        Payment myPayment = paymentRepository.findByPaymentId(paymentId);
        log.warn("Payment failed ");
        myPayment.setFailureReason(FailureReasons.PROVIDER_500);
        myPayment.setUpdatedAt(Instant.now());
        return Status.Failed;

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

    @Override
    public PaymentResponse getPaymentByIdempotencyKey(String idempotencyKey) {
        PaymentResponse response = mapToResponseFromEntity(paymentRepository.findByIdempotencyKey(idempotencyKey));
        return response;
    }


    private PaymentResponse mapToResponseFromEntity(Payment payment){
        PaymentResponse response = new PaymentResponse(payment.getId(),payment.getPaymentId(), payment.getOrderReference(), payment.getOrderId(), payment.getAmount(),payment.getStatus(),payment.getMethod(), payment.getProvider(), payment.getProviderPaymentId(),null);
   return response;

}


}
