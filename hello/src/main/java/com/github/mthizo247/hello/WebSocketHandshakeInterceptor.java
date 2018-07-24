package com.github.mthizo247.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.Map;

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    private static Logger logger = LoggerFactory.getLogger(HandshakeInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        System.out.println("WebSocketHandshakeInterceptor.beforeHandshake");

        if (request instanceof ServletServerHttpRequest) {
            System.out.println("request = [" + request + "], response = [" + response + "], wsHandler = [" + wsHandler + "], attributes = [" + attributes + "]");

            URI uri = request.getURI();
            System.out.println("uri = " + uri);
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;

            HttpServletRequest servletRequest1 = servletRequest.getServletRequest();
            String requestURI = servletRequest1.getRequestURI();
            System.out.println("requestURI = " + requestURI);
            String queryString = servletRequest1.getQueryString();


            System.out.println("queryString = " + queryString);

//            servletRequest1.getSession();
            HttpSession session = servletRequest1.getSession(false);
//            String id = session.getId();
//            System.out.println("id = " + id);
//
//            Enumeration<String> attributeNames = session.getAttributeNames();
//            while (attributeNames.hasMoreElements()) {
//                String s =  attributeNames.nextElement();
//                System.out.println("s = " + s);
//            }

//            HttpSession session = servletRequest1.getSession();
            if (session != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

        System.out.println("request = [" + request + "], response = [" + response + "], wsHandler = [" + wsHandler + "], exception = [" + exception + "]");

    }
}