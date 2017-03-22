package com.tj.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimePart;

// 2514036785@qq.com
public class Tmail {

	public static final String recipient = "2514036785@qq.com";
	public static final String sender = "laitaijun@163.com";
	public static final String myEmailSMTPHost = "smtp.163.com";

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", myEmailSMTPHost);
		props.setProperty("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("laitaijun@163.com", "LTJ20110548");
			}
		});
		session.setDebug(true);

		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(sender, "TMD", "UTF-8"));
			msg.setSubject("这TMD才是主题", "UTF-8");
			msg.setContent("今天晚上开车啊!", "text/plain;charset=UTF-8");
			msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipient, "MDZZ", "UTF-8"));
			msg.setSentDate(new Date());
			msg.saveChanges();

			Transport transport = session.getTransport();
			transport.connect();
			transport.send(msg);
			transport.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
