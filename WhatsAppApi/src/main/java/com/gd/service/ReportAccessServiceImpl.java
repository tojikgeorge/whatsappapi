package com.gd.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import com.gd.component.PropertyComponent;

@Service
@RequestScope
public class ReportAccessServiceImpl implements ReportAccessService {

	@Autowired
	PropertyComponent property;
	
	private final RestTemplate restTemplate;
	private String uri = "";
	
	public ReportAccessServiceImpl(RestTemplateBuilder builder)
	{
		this.restTemplate = builder.build();
	}
	@Override
	public String getPdfPublicUrl(String phoneNumber) 
	{
		//return "https://www.tutorialspoint.com/java/java_tutorial.pdf";
		////http://46.151.211.36:3002/report/0097145810754
		String result = "";
		this.uri = property.getReport_uri()+phoneNumber;

		try
		{
			Map<String, String> params = new HashMap<String, String>();
			params.put("phoneNumber", "0097145810754");
			 HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
		      HttpEntity <String> entity = new HttpEntity<String>(headers);
		      System.err.println("API Uri : "+this.uri);
		      result = restTemplate.exchange(this.uri, HttpMethod.GET, entity, String.class).getBody();
			//return restTemplate.getForObject("http://46.151.211.36:3002/reporturl/+9660561833664", String.class, "+97145810754");
			//result = restTemplate.getForObject("http://46.151.211.36:3002/report/", String.class, "0097145810754");
			//result = restTemplate.getForObject(uri, String.class, params);
		      //result = result.substring(result.indexOf("http"));
			System.err.println("Result : "+result);
			
			}
			catch(HttpClientErrorException e)
			{
				//result = e.getStatusCode() +" "+ e.getStatusText();
			}
			catch (Exception e) 
			{
			//result = "Something went wrong !";
			}
		
			return result;
	}

}
