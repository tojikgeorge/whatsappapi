package com.gd.dto;

public class WhatsAppReplyDataHolder 
{
	private String SmsMessageSid ="";
	private String NumMedia ="";
	private String ProfileName ="";
	private String SmsSid ="";
	private String WaId ="";
	private String SmsStatus ="";
	private String Body ="";
	private String To ="";
	private String NumSegments ="";
	private String MessageSid ="";
	private String AccountSid ="";
	private String From ="";
	private String ApiVersion ="";
	
	public String getSmsMessageSid() {
		return SmsMessageSid;
	}
	public void setSmsMessageSid(String smsMessageSid) {
		SmsMessageSid = smsMessageSid;
	}
	public String getNumMedia() {
		return NumMedia;
	}
	public void setNumMedia(String numMedia) {
		NumMedia = numMedia;
	}
	public String getProfileName() {
		return ProfileName;
	}
	public void setProfileName(String profileName) {
		ProfileName = profileName;
	}
	public String getSmsSid() {
		return SmsSid;
	}
	public void setSmsSid(String smsSid) {
		SmsSid = smsSid;
	}
	public String getWaId() {
		return WaId;
	}
	public void setWaId(String waId) {
		WaId = waId;
	}
	public String getSmsStatus() {
		return SmsStatus;
	}
	public void setSmsStatus(String smsStatus) {
		SmsStatus = smsStatus;
	}
	public String getBody() {
		return Body;
	}
	public void setBody(String body) {
		Body = body;
	}
	public String getTo() {
		return To;
	}
	public void setTo(String to) {
		To = to;
	}
	public String getNumSegments() {
		return NumSegments;
	}
	public void setNumSegments(String numSegments) {
		NumSegments = numSegments;
	}
	public String getMessageSid() {
		return MessageSid;
	}
	public void setMessageSid(String messageSid) {
		MessageSid = messageSid;
	}
	public String getAccountSid() {
		return AccountSid;
	}
	public void setAccountSid(String accountSid) {
		AccountSid = accountSid;
	}
	public String getFrom() {
		return From;
	}
	public void setFrom(String from) {
		From = from;
	}
	public String getApiVersion() {
		return ApiVersion;
	}
	public void setApiVersion(String apiVersion) {
		ApiVersion = apiVersion;
	}
	@Override
	public String toString() {
		return "WhatsAppReplyDataHolder [SmsMessageSid=" + SmsMessageSid + ", NumMedia=" + NumMedia + ", ProfileName="
				+ ProfileName + ", SmsSid=" + SmsSid + ", WaId=" + WaId + ", SmsStatus=" + SmsStatus + ", Body=" + Body
				+ ", To=" + To + ", NumSegments=" + NumSegments + ", MessageSid=" + MessageSid + ", AccountSid="
				+ AccountSid + ", From=" + From + ", ApiVersion=" + ApiVersion + "]";
	}	

}
