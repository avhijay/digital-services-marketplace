package com.marketplace.auth_service.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecurityAuditListner {

    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event){
        log.info("AUTH_SUCCESS user={} authorities {}",event.getAuthentication().getName(),event.getAuthentication().getAuthorities());


    }

    @EventListener
    public  void handleAuthenticationFailure(AbstractAuthenticationFailureEvent failureEvent){
        log.warn("AUTH_FAILURE user={} reason={}", failureEvent.getAuthentication().getName(),failureEvent.getException().getMessage());
    }
}
