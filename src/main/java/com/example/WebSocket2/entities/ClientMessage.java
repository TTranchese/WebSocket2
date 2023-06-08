package com.example.WebSocket2.entities;

public class ClientMessage {
	private String clientName;
	private String clientAlert;
	private String clientMsg;
	
	public ClientMessage(String clientName, String clientAlert, String clientMsg) {
		this.clientName = clientName;
		this.clientAlert = clientAlert;
		this.clientMsg = clientMsg;
	}
	
	public ClientMessage() {
	}
	
	public String getClientName() {
		return clientName;
	}
	
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getClientAlert() {
		return clientAlert;
	}
	
	public void setClientAlert(String clientAlert) {
		this.clientAlert = clientAlert;
	}
	
	public String getClientMsg() {
		return clientMsg;
	}
	
	public void setClientMsg(String clientMsg) {
		this.clientMsg = clientMsg;
	}
}
