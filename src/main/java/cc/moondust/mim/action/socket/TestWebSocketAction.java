package cc.moondust.mim.action.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tristan on 16/6/29.
 */
public class TestWebSocketAction extends TextWebSocketHandler {
    Logger log = LoggerFactory.getLogger(TestWebSocketAction.class);


    private List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.warn("afterConnectionEstablished");
        sessions.add(session);
        sessions.forEach(s -> {
            try {
                s.sendMessage(new TextMessage(session.getId()+"加入了聊天室"));
            } catch (IOException e) {
            }
        });
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.warn("handleMessage");
        super.handleMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        sessions.forEach(s -> {
            try {
                s.sendMessage(new TextMessage(session.getId()));
                s.sendMessage(message);
            } catch (IOException e) {
            }
        });
        log.warn("handleTextMessage");
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {

        log.warn("handleBinaryMessage");
    }


    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        log.warn("handlePongMessage");
        super.handlePongMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.forEach(s -> {
            try {
                s.sendMessage(new TextMessage(session.getId()+"离开了聊天室"));
            } catch (IOException e) {
            }
        });
        sessions.remove(session);
        super.afterConnectionClosed(session, status);
    }
}
