package com.healthmind.user.event;

import com.healthmind.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publishUserRegisteredEvent(User user, String correlationId){
        try{
            log.info("[{}] Publishing User Registered event for: {}", correlationId, user.getEmail());

            UserRegisteredEvent event =  UserRegisteredEvent.builder()
                    .userId(user.getId())
                    .email(user.getEmail())
                    .fullName(user.getFullName())
                    .role(user.getRole())
                    .correlationId(correlationId)
                    .build();

            eventPublisher.publishEvent(event);

            log.info("[{}] UserRegisteredEvent published successfully",
                    correlationId);
        }
        catch(Exception e){
            log.error("[{}] Failed to publish UserRegisteredEvent: {}",
                    correlationId, e.getMessage());
        }
    }
}
