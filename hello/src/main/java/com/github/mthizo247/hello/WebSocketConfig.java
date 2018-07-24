package com.github.mthizo247.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    public static Map<String, Message<?>> tokens = new ConcurrentHashMap<String, Message<?>>();

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		WebSocketHandshakeInterceptor interceptor = new WebSocketHandshakeInterceptor();
		registry.addEndpoint("/epld-websocket").
//                setHandshakeHandler(lkWsHandler).
//                addInterceptors(interceptor).
				// bypasses spring web security
				setAllowedOrigins("*").withSockJS();
	}

//	@Override
//	public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
//		System.out.println("registration = [" + registration + "]");
////		registration.
//		super.configureWebSocketTransport(registration);
//	}


    /**
     * 输入通道参数设置
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

        registration.interceptors(new ChannelInterceptorAdapter() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//                    MessageHeaders messageHeaders = accessor.getMessageHeaders();
//                    System.out.println("messageHeaders = " + messageHeaders);
//                    Authentication user = ... ; // access authentication header(s)
//                    accessor.setUser(user);
                    LinkedMultiValueMap nativeHeaders = (LinkedMultiValueMap) accessor.getHeader("nativeHeaders");
                    String token = (String) nativeHeaders.getFirst("token");
                    System.out.println("in token = " + token);
                    Principal principal = new Principal() {
                        @Override
                        public String getName() {
                            return token;
                        }
                        @Override
                        public boolean implies(Subject subject) {
                            Set<Principal> principals = subject.getPrincipals();
                            for (Iterator<Principal> iterator = principals.iterator(); iterator.hasNext(); ) {
                                Principal next =  iterator.next();
                                System.out.println("next = " + next);
                            }
                            return true;
                        }
                    };
                    accessor.setUser(principal);
                    if (tokens.containsKey(token)) {
                        System.err.println("已结存在连接 !! " + token);
                    } else {
                        tokens.put(token, message);
                    }
                }
                return message;
            }
        });
    }

//    @Override
//    public void configureClientOutboundChannel(ChannelRegistration registration) {
//        super.configureClientOutboundChannel(registration);
//        registration.interceptors(new ChannelInterceptorAdapter() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//                if (accessor == null) {
//                    System.out.println("configureClientOutboundChannel message = " + message);
//                    return message;
//                }
//                if (StompCommand.ACK.equals(accessor.getCommand())) {
//                    LinkedMultiValueMap nativeHeaders = (LinkedMultiValueMap) accessor.getHeader("nativeHeaders");
//                    String accountId = (String) nativeHeaders.getFirst("token");
//                    System.out.println("configureClientOutboundChannel accountId = " + accountId);
//                    Authentication user = new UsernamePasswordAuthenticationToken(accountId, accountId, AUTHORITIES); // access authentication header(s)
//                    accessor.setUser(user);
//                }
//                return message;
//            }
//        });
//    }

//	@Override
//	public void configureClientInboundChannel(ChannelRegistration registration) {
//		System.out.println("registration = [" + registration + "]");
//        ChannelRegistration interceptors = registration.interceptors();
//        System.out.println("interceptors = " + interceptors);
//        TaskExecutorRegistration taskExecutorRegistration = registration.taskExecutor();
//        System.out.println("taskExecutorRegistration = " + taskExecutorRegistration);
//
//        super.configureClientInboundChannel(registration);
//	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// prefix for subscribe
		config.enableSimpleBroker("/queue", "/topic");

		// prefix for send
		config.setApplicationDestinationPrefixes("/abc");

//		config.configureBrokerChannel();
	}

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        return super.configureMessageConverters(messageConverters);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        super.addReturnValueHandlers(returnValueHandlers);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
	public HttpSessionIdHandshakeInterceptor httpSessionIdHandshakeInterceptor() {
		return new HttpSessionIdHandshakeInterceptor();
	}

}
