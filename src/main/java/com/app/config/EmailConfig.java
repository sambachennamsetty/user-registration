package com.app.config;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component
public class EmailConfig {
	
	@Autowired
	private JavaMailSender mailSender;

	public void mailSend(String mailId,Integer otp,String subject)
	{
		try{

			// Creating MimeMessage Object
			MimeMessage message=mailSender.createMimeMessage();

			//2. Helper class object
			MimeMessageHelper helper=new MimeMessageHelper(message);

			helper.setTo(mailId);
			helper.setSubject(subject);
			helper.setFrom("samba@trackerwave.com");
			helper.setText("Your OTP is: "+otp);
			//sending mail
			mailSender.send(message);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
