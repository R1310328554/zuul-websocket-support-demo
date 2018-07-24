package com.github.mthizo247.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

/**
 * 监听订阅地址的用户
 */
@Component
public class StompUnsubscribeEventListener implements ApplicationListener<SessionUnsubscribeEvent> {
 
    private static final Logger logger = LoggerFactory.getLogger(StompUnsubscribeEventListener.class);
//    public final Map<String, Long> users = new ConcurrentHashMap();

    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String destination = headerAccessor.getDestination();
        String sessionId = headerAccessor.getSessionId();
        String subscriptionId = headerAccessor.getSubscriptionId();
        System.out.println("destination = " + destination + " " + sessionId + " " + subscriptionId);
    }


}
