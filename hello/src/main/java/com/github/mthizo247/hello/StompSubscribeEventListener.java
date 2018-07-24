package com.github.mthizo247.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * 监听订阅地址的用户
 */
@Component
public class StompSubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {
 
    private static final Logger logger = LoggerFactory.getLogger(StompSubscribeEventListener.class);
//    public final Map<String, Long> users = new ConcurrentHashMap();

    @Override
    public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
        //这里的sessionId对应HttpSessionIdHandshakeInterceptor拦截器的存放key
        // String sessionId = headerAccessor.getSessionAttributes().get(Constants.SESSIONID).toString();
//        logger.info("stomp Subscribe : "+headerAccessor.getMessageHeaders()  );
        String destination = headerAccessor.getDestination();
        String sessionId = headerAccessor.getSessionId();
        String subscriptionId = headerAccessor.getSubscriptionId();
        System.out.println("destination = " + destination + " " + sessionId + " " + subscriptionId);
//        destination = destination.substring("/user/".length());
//        int secondBackslashInd = destination.indexOf("/");
//        String userId = destination.substring(0, secondBackslashInd);
//        System.out.println("userId = " + userId);

//        users.put(userId, System.currentTimeMillis());
//        ((GenericMessage)((SessionSubscribeEvent)sessionSubscribeEvent).message).headers).entrySet().toArray()[8]
    }


}
