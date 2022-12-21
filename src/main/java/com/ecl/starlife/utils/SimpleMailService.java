package com.ecl.starlife.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleMailService {

	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}" )String mailUsername;

	private static final Logger logger = LoggerFactory.getLogger(SimpleMailService.class);

	@Autowired
	public  SimpleMailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendNotification(String user_email, String msg, String sub)
			throws MailException {


		try {

			logger.info("sending mail...");

			//send email
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(user_email);
			mail.setFrom(mailUsername);
			mail.setSubject(sub);
			mail.setText(msg);
			javaMailSender.send(mail);

			logger.info("mail sent!");


		}catch(Exception ex) {
			logger.error("failed to send mail " + ex);
		}

	}


}
