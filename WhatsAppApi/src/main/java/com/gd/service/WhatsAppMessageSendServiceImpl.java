package com.gd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import java.net.URI;
import java.util.Arrays;
import java.util.Objects;

import com.gd.component.PropertyComponent;
import com.gd.dto.WhatsAppMessageHolder;

@Service
@RequestScope
public class WhatsAppMessageSendServiceImpl implements WhatsAppMessageSendService
{
	private static final String WHATSAPP="whatsapp:";
	
	@Autowired
	PropertyComponent property;

	@Override
	public String sendMessage(final String recipientNumber, final String templateName, final String data, final String mediaUrl)
	{
		if( Objects.isNull(mediaUrl) )
		{
			WhatsAppMessageHolder dataHolder = new WhatsAppMessageHolder();
			dataHolder.setSenderNumber(property.getSendNumber());
			dataHolder.setRecipientNumber(formatNumberAsWhatsAppNUmber(recipientNumber));
			dataHolder.setMessage(prepareWhatsAppMessageBasedOnTemplate(templateName, data));
			System.out.println("################  -->  "+dataHolder.getMessage());
			return sendWhatsAppMessage(dataHolder);
		}
		else
		{
			try
			{
				sendMediaMessage(recipientNumber,mediaUrl);
				return "Media Message";
			}
			catch (Exception e)
			{
				return e.toString();
			}
		}
				
	}
	
	@Override
	public void sendMediaMessage(final String recipientNumber, final String mediaUrl)throws Exception
	{System.out.println("3 ");
		WhatsAppMessageHolder dataHolder = new WhatsAppMessageHolder();
		dataHolder.setSenderNumber(formatNumberAsWhatsAppNUmber(property.getSendNumber()));
		dataHolder.setRecipientNumber(formatNumberAsWhatsAppNUmber(recipientNumber));
		dataHolder.setPdfUrl(mediaUrl);		
		sendWhatsAppMediaMessage(dataHolder);
	}
	
	private String sendWhatsAppMessage(final WhatsAppMessageHolder dataHolder)
	{
		try {
			String ACCOUNT_SID = property.getAccountSid();
			String AUTH_TOKEN = property.getAuthKey();
			
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		PhoneNumber recipientNumber = new PhoneNumber(dataHolder.getRecipientNumber());
		PhoneNumber senderNumber = new PhoneNumber(dataHolder.getSenderNumber());
		Message message = Message.creator(
				recipientNumber,
				senderNumber,
	               dataHolder.getMessage())
	           .create();
		return "SUCCESS"+"  "+message.getSid()+"  "+message.getStatus()+" "+message.getSid()+"  "+message.getStatus();
		}
		catch(Exception e)
		{
			return e.toString();
		}
		
	}
	
	private void sendWhatsAppMediaMessage(final WhatsAppMessageHolder dataHolder) throws Exception
	{
		try {System.out.println("4 ");
			String ACCOUNT_SID = property.getAccountSid();
			String AUTH_TOKEN = property.getAuthKey();
			
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		PhoneNumber recipientNumber = new PhoneNumber(dataHolder.getRecipientNumber());
		PhoneNumber senderNumber = new PhoneNumber(dataHolder.getSenderNumber());
		Message message = Message.creator(
				recipientNumber,
				senderNumber,
				Arrays.asList(URI.create(dataHolder.getPdfUrl())))
	           .create();
		System.out.println("5 ");
		}
		catch(Exception e)
		{System.out.println("6 ");
			throw e;
		}
		
	}
	
	private String formatNumberAsWhatsAppNUmber(final String val)
	{
		if(val.startsWith("whatsapp:"))
		{
			return val;
		}
		else
		{
			if(val.startsWith("+"))
			{
				return "whatsapp:"+val;
			}
			else
			{
				return "whatsapp:+"+val;
			}
		}
		
	}
	
	private String prepareWhatsAppMessageBasedOnTemplate(final String templateKey, final String data)
	{
		String message = " ";
		if("get_result_report_4".equals(templateKey))
		{
			message = String.format(property.getResult_report_4(),getCorrectValueFromArray(1, data));
		}
		else if("get_result_report_3".equals(templateKey))
		{
			message = String.format(property.getResult_report_3(),
					getCorrectValueFromArray(1, data),
					getCorrectValueFromArray(2, data),
					getCorrectValueFromArray(3, data));
		}
		else if("get_result_report_1".equals(templateKey))
		{
			message = String.format(property.getResult_report_1(),getCorrectValueFromArray(1, data));
		}
		else
		{
			message = data;
		}
		return message;
	}
	
	private String getCorrectValueFromArray(final int index, String data)
	{
		String [] splitedData = data.split(",");
		if(index-1 < splitedData.length)
		{
			return splitedData[index-1];
		}
		else
		{
			return " ";
		}
	}
	
}
