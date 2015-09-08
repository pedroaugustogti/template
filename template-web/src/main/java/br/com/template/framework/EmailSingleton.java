package br.com.template.framework;

import java.util.Date;

import javax.ejb.Asynchronous;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.http.MediaType;

/* This class is responsible for actually processing
 * "Mail Events" and sending emails using the Java Mail API.
 * 
 * Note that this class is an EJB.
 * 
 */
@Singleton
public class EmailSingleton {
	
	/* Take a look at the ResourcesProducer class */
	@Inject
	private Session mailSession;
	
	
	/* To make the mail sending routine asynchronous, we annotate this method
	 * with @Asynchronous and @Lock(READ). These are EJB annotations, not CDI.
	 * 
	 * The second thing to pay attention to, is the CDI @Observers annotation and 
	 * its "during = AFTER_SUCCESS" attribute. This is what guarantees that this event
	 * will only be processed if the transaction commits successfully. */
	@Asynchronous
	@Lock(LockType.READ)
	public void sendMail(@Observes(during = TransactionPhase.AFTER_SUCCESS) EmailDTO event) {

		try {
            MimeMessage m = new MimeMessage(mailSession);
            Address[] to = new InternetAddress[] {new InternetAddress(event.getTo())};

            m.setRecipients(Message.RecipientType.TO, to);
            m.setSubject(event.getSubject());
            m.setSentDate(new Date());
            m.setContent(event.getMessage(), MediaType.TEXT_HTML_VALUE); 
            
            Transport.send(m);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
}
