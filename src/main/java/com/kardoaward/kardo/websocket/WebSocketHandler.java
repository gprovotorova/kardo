package com.kardoaward.kardo.websocket;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WebSocketHandler extends AbstractWebSocketHandler {

    private static Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        sessions.add(session);
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session,
                                       BinaryMessage message)
            throws Exception {
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) {
                try {
                    webSocketSession.sendMessage(new BinaryMessage(message.getPayload()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
