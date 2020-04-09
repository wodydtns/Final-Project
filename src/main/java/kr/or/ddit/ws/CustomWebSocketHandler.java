package kr.or.ddit.ws;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class CustomWebSocketHandler extends TextWebSocketHandler{
	private static Logger logger = 
			LoggerFactory.getLogger(CustomWebSocketHandler.class);
	
	@Resource(name="wsSessionList")
	List<WebSocketSession> wsSessionList;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		wsSessionList.add(session);
		Map<String, Object> attributes = session.getAttributes();
		Principal principal = session.getPrincipal();
		if(principal!=null)
			logger.info("principal : {}", principal.getName());
		logger.info("HttpSession attributes : {}", attributes);
		logger.info("{} 연결수립", session.getRemoteAddress());
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		wsSessionList.remove(session);
		logger.info("{} 연결종료", session.getRemoteAddress());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for(WebSocketSession tmp  : wsSessionList) {
			Principal principal = session.getPrincipal();
			String sender = "익명";
			if(principal!=null) {
				sender = principal.getName();
			}
			tmp.sendMessage(new TextMessage(sender+":"+message.getPayload()));
		}
	}
}