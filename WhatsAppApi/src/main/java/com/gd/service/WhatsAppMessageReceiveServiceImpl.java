package com.gd.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.gd.component.PropertyComponent;
import com.gd.dto.WhatsAppReplyDataHolder;

@Service
@RequestScope
public class WhatsAppMessageReceiveServiceImpl implements WhatsAppMessageReceiveService 
{
	private static final String YES="Yes";
	private static final String NO="No";
	
	@Autowired
	WhatsAppMessageSendService sendService;
	@Autowired
	ReportAccessService reportAccess;
	@Autowired
	PropertyComponent property;

	@Override
	public String processReceiveMessage(HttpServletRequest request) 
	{
		if(null != request)
		{
			try
			{
			WhatsAppReplyDataHolder data = populateReplyInfo(request);
			
			if(YES.equalsIgnoreCase(data.getBody()))
			{
				String pdfUrl = reportAccess.getPdfPublicUrl(data.getFrom().replace("whatsapp:", ""));
				System.out.println("pdfUrl   --> "+pdfUrl);
				try
				{
					sendService.sendMediaMessage(data.getFrom(), pdfUrl);
					//return String.format(property.getThanks(),data.getProfileName());
					sendService.sendMessage(data.getFrom(), "",String.format(property.getThanks(),data.getProfileName()),"");
				}
				catch(Exception e)
				{
					System.err.println(e.toString());
					if(pdfUrl != null && !pdfUrl.trim().isEmpty())
					{
						sendService.sendMessage(data.getFrom(), "", pdfUrl, "");
					}
				}
				
			}
			else if(NO.equalsIgnoreCase(data.getBody()))
			{
				//return String.format(property.getThanks(),data.getProfileName());
				sendService.sendMessage(data.getFrom(), "",String.format(property.getThanks(),data.getProfileName()),"");
			}
			else
			{
				//return String.format(property.getCorrectValue(),data.getProfileName());
				sendService.sendMessage(data.getFrom(), "",String.format(property.getCorrectValue(),data.getProfileName()),"");
			}
			}
			catch(Exception e)
			{
				return e.toString();
			}
		}
		return property.getError();
	}
	
	private WhatsAppReplyDataHolder populateReplyInfo(HttpServletRequest request)
	{
		WhatsAppReplyDataHolder data = new WhatsAppReplyDataHolder();
		data.setAccountSid(nullCheckAndTrim(request.getParameter("AccountSid")));
		data.setApiVersion(nullCheckAndTrim(request.getParameter("ApiVersion")));
		data.setBody(nullCheckAndTrim(request.getParameter("Body")));
		data.setFrom(nullCheckAndTrim(request.getParameter("From")));
		data.setMessageSid(nullCheckAndTrim(request.getParameter("MessageSid")));
		data.setNumMedia(nullCheckAndTrim(request.getParameter("NumMedia")));
		data.setNumSegments(nullCheckAndTrim(request.getParameter("NumSegments")));
		data.setProfileName(nullCheckAndTrim(request.getParameter("ProfileName")));
		data.setSmsMessageSid(nullCheckAndTrim(request.getParameter("SmsMessageSid")));
		data.setSmsSid(nullCheckAndTrim(request.getParameter("SmsSid")));
		data.setSmsStatus(nullCheckAndTrim(request.getParameter("SmsStatus")));
		data.setTo(nullCheckAndTrim(request.getParameter("To")));
		data.setWaId(nullCheckAndTrim(request.getParameter("WaId")));
		return data;
	}
	
	private String nullCheckAndTrim(final String val)
	{
		if(null == val)
		{
			return "";
		}
		else
		{
			return val.trim();
		}
	}

}
