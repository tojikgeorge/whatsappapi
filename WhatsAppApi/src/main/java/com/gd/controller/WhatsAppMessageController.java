package com.gd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.gd.component.PropertyComponent;
import com.gd.service.WhatsAppMessageReceiveService;
import com.gd.service.WhatsAppMessageSendService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestScope
public class WhatsAppMessageController 
{
	@Autowired
	WhatsAppMessageSendService sendService;
	
	@Autowired
	WhatsAppMessageReceiveService receiveService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	PropertyComponent propertyComponent;
	
	@PostMapping(value="/whatsapp/v1/send")
	public ResponseEntity<String> sendMessage(@RequestParam String phoneNumber, @RequestParam(required = false, value = "") String templateName, @RequestParam(required = false, value = "") String data, @RequestParam(required = false, value = "") String mediaUrl)
	{
		String result="";
		try
		{
	
		if(null == phoneNumber || phoneNumber.trim() == "")
		{
			result = "Enter valid phone number !";
		}
		
		else
		{
			result = sendService.sendMessage(phoneNumber.trim(), templateName, data, mediaUrl);
		}

		return ResponseEntity.ok(result);
		}
		catch(Exception e)
		{
			return ResponseEntity.ok(e.toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value="/whatsapp/v1/receive")
	public void receiveMessage(@RequestBody String requestBody, @RequestParam String token)
	{
		
		System.out.println("Call from WhatsApp! "+requestBody);
		if( token == null || !(token.trim().equals(propertyComponent.getToken())))
		{
			  ResponseEntity.internalServerError();	
		}

		System.out.println("1 ");
		String status = receiveService.processReceiveMessage(request);
		System.out.println(status);
		
		 ResponseEntity.ok(status);
	}
	
}
