package com.marketplace.order_service.client.health;




import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class OrderReadinessIndicator implements HealthIndicator {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public OrderReadinessIndicator(CircuitBreakerRegistry circuitBreakerRegistry){
        this.circuitBreakerRegistry=circuitBreakerRegistry;
    }


    @Override
    public Health health() {


        CircuitBreaker userClientCircuitBreaker = circuitBreakerRegistry.circuitBreaker("userClient");
        CircuitBreaker.State state = userClientCircuitBreaker.getState();


        if (state == CircuitBreaker.State.OPEN) {
            return Health.down()
                    .withDetail("userService", "UNAVAILABLE")
                    .withDetail ("circuitBreakerState",state.name()).build();
        }

        return Health.up().withDetail("userService", "AVAIALBLE")
                .withDetail("circuitBreakerState", state.name())
                .build();

    }

}



