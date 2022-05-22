package com.gd.service;


public interface WhatsAppMessageSendService 
{
	String sendMessage(final String recipientNumber, final String templateName, String data, final String mediaUrl);
	void sendMediaMessage(final String recipientNumber, final String mediaUrl) throws Exception;
}
