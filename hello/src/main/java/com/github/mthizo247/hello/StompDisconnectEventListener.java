package com.github.mthizo247.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

/**
 * 监听订阅地址的用户
 */
@Component
public class StompDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {
 
    private static final Logger logger = LoggerFactory.getLogger(StompDisconnectEventListener.class);

    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        Principal user = sessionDisconnectEvent.getUser();
        Object source = sessionDisconnectEvent.getSource();
        String username = user.getName();
        boolean b = WebSocketConfig.tokens.containsKey(username);
        if (b) {
            System.out.println("user = " + username);
            WebSocketConfig.tokens.remove(username);
        }
    }
}
