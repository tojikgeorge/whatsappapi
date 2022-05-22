package com.gd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration()
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordEncode;
	
	private final String userSend = "senduser";	
	private String sendPassword = "Gkfh*O3k2jf_j#D";	

	
	@Autowired
	public ApplicationSecurity(PasswordEncoder passwordEncode) {
		this.passwordEncode  =  passwordEncode;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/whatsapp/v1/send").hasRole(ApplicationRole.SEND.name())
		.antMatchers("/whatsapp/v1/receive").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		
		UserDetails sendUser = User.builder()
				.username(userSend)
				.password(passwordEncode.encode(sendPassword))
				.roles(ApplicationRole.SEND.name())
				.build();
		
		UserDetails receiveUser = User.builder()
				.username("userReceive")
				.password(passwordEncode.encode("receivePassword"))
				.roles(ApplicationRole.RECEIVE.name())
				.build();
		
		return new InMemoryUserDetailsManager(sendUser,receiveUser);
		
		
	}

}
