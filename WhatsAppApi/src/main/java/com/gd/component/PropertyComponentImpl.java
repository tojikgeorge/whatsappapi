package com.gd.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyComponentImpl implements PropertyComponent
{
	@Value( "${api.whatsapp.account.sid}" )
	private String accountSid;
	@Value( "${api.whatsapp.auth.key}" )
	private String authKey;
	@Value( "${api.whatsapp.send.number}" )
	private String sendNumber;
	@Value( "${api.whatsapp.template2}" )
	private String template2;
	@Value( "${api.whatsapp.thanks}" )
	private String thanks;
	@Value( "${api.whatsapp.correct.value}" )
	private String correctValue;
	@Value( "${api.whatsapp.error}" )
	private String error;
	@Value( "${api.whatsapp.get_result_report_1}" )
	private String result_report_1;
	@Value( "${api.whatsapp.receive.token}" )
	private String token;
	@Value( "${api.whatsapp.get_result_report_3}" )
	private String result_report_3;
	@Value( "${api.whatsapp.get_result_report_4}" )
	private String result_report_4;
	@Value( "${api.whatsapp.report.uri}" )
	private String report_uri; 
	
	public String getAccountSid() {
		return accountSid;
	}
	public String getAuthKey() {
		return authKey;
	}
	public String getSendNumber() {
		return sendNumber;
	}
	public String getTemplate2() {
		return template2;
	}
	public String getThanks() {
		return thanks;
	}
	public String getCorrectValue() {
		return correctValue;
	}
	public String getError() {
		return error;
	}
	public String getResult_report_1() {
		return result_report_1;
	}
	public String getToken() {
		return token;
	}
	public String getResult_report_3() {
		return result_report_3;
	}
	public String getResult_report_4() {
		return result_report_4;
	}
	public String getReport_uri() {
		return report_uri;
	}
	
	
	
	

}
