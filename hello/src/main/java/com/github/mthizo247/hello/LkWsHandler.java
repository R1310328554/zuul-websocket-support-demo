package com.github.mthizo247.hello;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketExtension;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.server.RequestUpgradeStrategy;
import org.springframework.web.socket.server.support.AbstractHandshakeHandler;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Created by L.K. on 2018/7/9
 */
public class LkWsHandler extends AbstractHandshakeHandler {

    @Override
    public RequestUpgradeStrategy getRequestUpgradeStrategy() {
        return super.getRequestUpgradeStrategy();
    }

    @Override
    public void setSupportedProtocols(String... protocols) {
        super.setSupportedProtocols(protocols);
    }

    @Override
    public String[] getSupportedProtocols() {
        System.out.println("WebSocketConfig.getSupportedProtocols");
        return super.getSupportedProtocols();
    }

    @Override
    public void start() {
        System.out.println("WebSocketConfig.start");
        super.start();
    }

    @Override
    protected void doStart() {
        System.out.println("WebSocketConfig.doStart");
        super.doStart();
    }

    @Override
    public void stop() {
        System.out.println("WebSocketConfig.stop");
        super.stop();
    }

    @Override
    protected void doStop() {
        System.out.println("WebSocketConfig.doStop");
        super.doStop();
    }

    @Override
    public boolean isRunning() {
        System.out.println("WebSocketConfig.isRunning");
        return super.isRunning();
    }

    @Override
    protected void handleInvalidUpgradeHeader(ServerHttpRequest request, ServerHttpResponse response) throws IOException {
        System.out.println("WebSocketConfig.handleInvalidUpgradeHeader");
        super.handleInvalidUpgradeHeader(request, response);
    }

    @Override
    protected void handleInvalidConnectHeader(ServerHttpRequest request, ServerHttpResponse response) throws IOException {
        System.out.println("WebSocketConfig.handleInvalidConnectHeader");
        super.handleInvalidConnectHeader(request, response);
    }

    @Override
    protected boolean isWebSocketVersionSupported(WebSocketHttpHeaders httpHeaders) {
        System.out.println("WebSocketConfig.isWebSocketVersionSupported");
        return super.isWebSocketVersionSupported(httpHeaders);
    }

    @Override
    protected String[] getSupportedVersions() {
        return super.getSupportedVersions();
    }

    @Override
    protected void handleWebSocketVersionNotSupported(ServerHttpRequest request, ServerHttpResponse response) {
        super.handleWebSocketVersionNotSupported(request, response);
    }

    @Override
    protected boolean isValidOrigin(ServerHttpRequest request) {
        return super.isValidOrigin(request);
    }

    @Override
    protected String selectProtocol(List<String> requestedProtocols, WebSocketHandler webSocketHandler) {
        return super.selectProtocol(requestedProtocols, webSocketHandler);
    }

    @Override
    protected List<WebSocketExtension> filterRequestedExtensions(ServerHttpRequest request, List<WebSocketExtension> requestedExtensions, List<WebSocketExtension> supportedExtensions) {
        System.out.println("WebSocketConfig.filterRequestedExtensions");
        return super.filterRequestedExtensions(request, requestedExtensions, supportedExtensions);
    }

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        System.out.println("WebSocketConfig.determineUser");
        return super.determineUser(request, wsHandler, attributes);
    }

}
