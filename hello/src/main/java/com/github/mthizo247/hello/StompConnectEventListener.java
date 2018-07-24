package com.github.mthizo247.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.security.Principal;

/**
 * 监听订阅地址的用户
 */
@Component
public class StompConnectEventListener implements ApplicationListener<SessionConnectedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(StompConnectEventListener.class);

    @Override
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
        Principal user = sessionConnectedEvent.getUser();
        Object source = sessionConnectedEvent.getSource();
        System.out.println("user = " + user);
    }
}
