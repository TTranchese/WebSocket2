package com.example.WebSocket2.controllers;

import com.example.WebSocket2.entities.ClientMessage;
import com.example.WebSocket2.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BasicController {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@PostMapping("/broadcast-message")
	public ResponseEntity<String> sendBroadcastMessage(@RequestBody Message message) {
		try {
			messagingTemplate.convertAndSend("/topic/broadcast", message);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@MessageMapping("/client-message")
	@SendTo("/topic/broadcast")
	public Message socketMessageHandler(ClientMessage clientMessage) {
		System.out.printf("Arrived something on app/hello : %s", clientMessage);
		return new Message("Message from client " + clientMessage.getClientName(), " Message: "
				+ clientMessage.getClientAlert(), "Alert: " + clientMessage.getClientMsg());
	}
	
}
