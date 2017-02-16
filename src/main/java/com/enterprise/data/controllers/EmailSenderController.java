package com.enterprise.data.controllers;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@Controller
public class EmailSenderController {

	@Autowired
	 private JavaMailSender mailSender;
	 

	
	@RequestMapping(method = RequestMethod.GET, value = "/mail")
	public void sendMail(@RequestParam(value="name", required=true) String name, @RequestParam(value="phone", required=true) String phone){
		
		System.out.println("phone="+phone);
		System.out.println("name="+name);
	
		   MimeMessagePreparator preparator = new MimeMessagePreparator() {

	            public void prepare(MimeMessage mimeMessage) throws Exception {

	            	//mimeMessage.s
	                mimeMessage.setRecipient(Message.RecipientType.TO,
	                        new InternetAddress("armiax@gmail.com"));
	                mimeMessage.setFrom(new InternetAddress("site@mycompany.com"));
	                mimeMessage.setText(
	                        "Имя: " + name + ", телефон: " + phone);
		            }
	        };

	        try {
	            this.mailSender.send(preparator);
	        }
	        catch (MailException ex) {
	            // simply log it and go on...
	            System.err.println(ex.getMessage());
	        }
	    }

		
}

