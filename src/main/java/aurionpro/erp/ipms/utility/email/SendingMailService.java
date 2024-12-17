package aurionpro.erp.ipms.utility.email;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.utility.AppProperties;

@Service
public class SendingMailService {
	
	@Autowired
    private AppProperties appProperties;
	
	@Autowired
    private EmailRepository emailRepo;
	
	public Map<String, String> sendEmail(String listOfToAddress, String subject, String msgBody, String attachment) {

		final String username = appProperties.getMailUserName;
		final String password = appProperties.getMailPassword;

		Map<String, String> response = new LinkedHashMap<String, String>();
		Properties properties = new Properties();

		properties.setProperty("mail.smtp.auth", appProperties.getAuthFlag);
		properties.setProperty("mail.smtp.starttls.enable", appProperties.getAuthFlag);
		properties.setProperty("mail.smtp.host", appProperties.getHost);
		properties.setProperty("mail.smtp.port", appProperties.getSocketport);
		properties.setProperty("mail.smtp.socketFactory.port", appProperties.getSocketport);

		if ("true".equals(appProperties.getIsSSL)) {
			if ("true".equals(appProperties.getAuthFlag)) {
				properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			}
		}

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(appProperties.getMailUserName));
			message.setSubject(subject);
		    message.setContent(msgBody, "text/html");

			InternetAddress toAddress;
			if (!StringUtils.isEmpty(listOfToAddress)) {
				toAddress = new InternetAddress(listOfToAddress);
				message.addRecipient(Message.RecipientType.TO, toAddress);
			}
			if (!StringUtils.isEmpty(attachment)) {
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(msgBody, "text/html");

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				messageBodyPart = new MimeBodyPart();
				// String filename = attachment;
				DataSource source = new FileDataSource(attachment);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(attachment.substring(attachment.lastIndexOf("/") + 1));
				multipart.addBodyPart(messageBodyPart);

				message.setContent(multipart);
			} else {
				message.setContent(msgBody, "text/html");
			}

			Transport.send(message);
			
			Email email = new Email();
			
			email.setEmailid(listOfToAddress);
			email.setEmailsubject(subject);
			email.setEmailbody(msgBody);
			email.setEmailstatus("SEND");
			email.setSenddate(System.currentTimeMillis());
			
			emailRepo.save(email);
			
			
		} catch (MessagingException e) {
			Email email = new Email();
			
			email.setEmailid(listOfToAddress);
			email.setEmailsubject(subject);
			email.setEmailbody(msgBody);
			email.setEmailstatus("QUEUED");
			email.setSenddate(System.currentTimeMillis());
			
			emailRepo.save(email);
		}

		//logger.info("Method : ReadyToSendEmail : End");
		return response;
		
	}

	public Map<String, String> sendEmail(String listOfToAddress, String listOfCcAddress, String listOfBccAddress,
			String subject, String msgBody, String attachment) {
		
		 System.out.println("Enter Mail Service");
		final String username = appProperties.getMailUserName;
		final String password = appProperties.getMailPassword;

		Map<String, String> response = new LinkedHashMap<String, String>();
		Properties properties = new Properties();

		properties.setProperty("mail.smtp.auth", appProperties.getAuthFlag);
		properties.setProperty("mail.smtp.starttls.enable", appProperties.getAuthFlag);
		properties.setProperty("mail.smtp.host", appProperties.getHost);
		properties.setProperty("mail.smtp.port", appProperties.getSocketport);
		properties.setProperty("mail.smtp.socketFactory.port", appProperties.getSocketport);

		if ("true".equals(appProperties.getIsSSL)) {
			if ("true".equals(appProperties.getAuthFlag)) {
				properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			}
		}

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(appProperties.getMailUserName));
			message.setSubject(subject);
			message.setContent(msgBody, "text/html");
			 System.out.println("Enter Try Catch Mail");
			String[] toEmailList = null, ccEmailList = null, bccEmailList = null;
			InternetAddress[] toAddress, ccAddress, bccAddress;

			if (!StringUtils.isEmpty(listOfToAddress)) {
				toEmailList = listOfToAddress.split(";");
				toAddress = new InternetAddress[toEmailList.length];
				for (int i = 0; i < toEmailList.length; i++) {
					toAddress[i] = new InternetAddress(toEmailList[i]);
					message.addRecipient(Message.RecipientType.TO, toAddress[i]);
				}
				
			}
			if (!StringUtils.isEmpty(listOfCcAddress)) {
				ccEmailList = listOfCcAddress.split(";");
				ccAddress = new InternetAddress[ccEmailList.length];
				for (int i = 0; i < ccEmailList.length; i++) {
					ccAddress[i] = new InternetAddress(ccEmailList[i]);
					message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
				}
			}
			if (!StringUtils.isEmpty(listOfBccAddress)) {
				bccEmailList = listOfBccAddress.split(";");
				bccAddress = new InternetAddress[bccEmailList.length];
				for (int i = 0; i < bccEmailList.length; i++) {
					bccAddress[i] = new InternetAddress(bccEmailList[i]);
					message.addRecipient(Message.RecipientType.BCC, bccAddress[i]);
				}
			}

			if (!StringUtils.isEmpty(attachment)) {
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(msgBody, "text/html");

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);
				
				System.out.println("Message body");
				
				messageBodyPart = new MimeBodyPart();
				// String filename = attachment;
				DataSource source = new FileDataSource(attachment);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(attachment.substring(attachment.lastIndexOf("/") + 1));
				multipart.addBodyPart(messageBodyPart);

				message.setContent(multipart);
			} else {
				System.out.println("Message body");
				message.setContent(msgBody, "text/html");
			}
			System.out.println("Send Mail");
			try {
				Transport.send(message);
			}
			catch(Exception e ) {
				e.printStackTrace();
				System.out.println("Transport catch");
			}
			System.out.println("Mail Sent.. Save in table");
			Email email = new Email();
			
			email.setEmailid(listOfToAddress);
			email.setCcemailid(listOfCcAddress);
			email.setBccemailid(listOfBccAddress);
			email.setEmailsubject(subject);
			email.setEmailbody(msgBody);
			email.setEmailstatus("SEND");
			email.setSenddate(System.currentTimeMillis());
			
			emailRepo.save(email);
			System.out.println("Saved in table");
			
		} catch (MessagingException e) {
			System.out.println("Enter Exception");
			e.printStackTrace();
			Email email = new Email();
			
			email.setEmailid(listOfToAddress);
			email.setCcemailid(listOfCcAddress);
			email.setBccemailid(listOfBccAddress);
			email.setEmailsubject(subject);
			email.setEmailbody(msgBody);
			email.setEmailstatus("QUEUED");
			email.setSenddate(System.currentTimeMillis());
			
			emailRepo.save(email);
			
			System.out.println("Leave Exception Mail");
		}
		System.out.println("Leave Mail Service");
		//logger.info("Method : ReadyToSendEmail : End");
		return response;
		//;o
	}
	
	public Email queueEmail(Email email) {
		email.setEmailstatus("QUEUED");
		return emailRepo.save(email);
	}
	
	public Email queueEmail(String listOfToAddress, String listOfCcAddress, String listOfBccAddress,
			String subject, String msgBody, String attachment) {
				 
			Email email = new Email();
			
			email.setEmailid(listOfToAddress);
			email.setCcemailid(listOfCcAddress);
			email.setBccemailid(listOfBccAddress);
			email.setEmailsubject(subject);
			email.setEmailbody(msgBody);
			email.setEmailstatus("QUEUED");
			email.setSenddate(System.currentTimeMillis());
			
			return emailRepo.save(email);
			
	}
}