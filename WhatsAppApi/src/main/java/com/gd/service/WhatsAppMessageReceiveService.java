package com.gd.service;

import javax.servlet.http.HttpServletRequest;

public interface WhatsAppMessageReceiveService {
	
	String processReceiveMessage(HttpServletRequest request);
}
