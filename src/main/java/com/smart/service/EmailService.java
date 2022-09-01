package com.smart.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public boolean sendEmail(String subject, String message, String to) {

		// rest of the code

		boolean f = false;

		String from = "akash217burde@gmail.com";

		// Variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();

		System.out.println("PROPERTIES " + properties);

		// setting important information to properties object

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1: to get the session object..

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("akash217burde@gmail.com", "jztkxnjjhcflycex");
			}

		});

		session.setDebug(true);

		// step 2: compose the message [text,multi media]

		MimeMessage m = new MimeMessage(session);

		try {

			// from email
			m.setFrom();

			// adding recipient to massage
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding sub to message
			m.setSubject(subject);

			// adding text to message
			//m.setText(message);
			m.setContent(message,"text/html");

			// send

			// Step 3 : send the message using transport class
			Transport.send(m);

			System.out.println("sent success.........");
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;

	}
}
