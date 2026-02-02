package com.marketplace.payment_service.state;

import com.marketplace.payment_service.enums.Status;
import com.marketplace.payment_service.exception.InvalidPaymentStateTransitionException;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class PaymentStateMachine {

    private final Map<Status , Set<Status>> transitionsAllowed = new EnumMap<>(Status.class);



    public  PaymentStateMachine(){
        transitionsAllowed.put(Status.CREATED, EnumSet.of(Status.PROCESSING));

        transitionsAllowed.put(Status.PROCESSING,EnumSet.of(Status.COMPLETED,Status.Failed));

        transitionsAllowed.put(Status.Failed,EnumSet.of(Status.PROCESSING));

        transitionsAllowed.put(Status.COMPLETED,EnumSet.noneOf(Status.class));
    }



    public void assertTransition(Status current , Status next ){
        Set<Status>allowed = transitionsAllowed.get(current);
        if (allowed==null || !allowed.contains(next)){
            throw  new InvalidPaymentStateTransitionException("Invalid transition of payment state : "+current +"to"+next);
        }
    }

}
