package com.javaee.fabiola.acoes.emailsender;

public class EmailSender {

	public void SendEmail(String toEmail, String subject, String body) {
		final String fromEmail = "fafa@hotmail.com";
		final String password = "abc123";
		
		System.out.println("Initializing email send");
		
		EmailConfig config = new EmailConfig();
		
		config.sendEmail(fromEmail, password, toEmail, subject, body);
	}

}
