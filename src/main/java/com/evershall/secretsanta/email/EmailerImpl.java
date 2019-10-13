package com.evershall.secretsanta.email;

import com.evershall.secretsanta.data.SantaRecord;
import org.slf4j.Logger;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;

import static com.evershall.secretsanta.email.EmailBodyFactory.constructMessageText;
import static com.evershall.secretsanta.email.PropertyHelper.getPropertyOrDie;
import static java.lang.System.*;
import static org.slf4j.LoggerFactory.getLogger;

public class EmailerImpl implements Emailer {

   private static final Logger LOG = getLogger(EmailerImpl.class);

   @Override
   public void send(final SantaRecord buyer, final SantaRecord receiver) {

      try {
         setIfMissing("mail.smtp.host", "smtp.gmail.com");
         setIfMissing("mail.smtp.port", "587");
         setIfMissing("mail.smtp.auth", "true");
         setIfMissing("mail.smtp.starttls.enable", "true");

         final Session session1 = Session.getInstance(getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(getProperty("mail.smtp.user"), getProperty("mail.smtp.password"));
            }
         });

         final MimeMessage message = createMessage(buyer, receiver, session1, getProperty("mail.smtp.user"));
         Transport.send(message);
         LOG.debug("Sent message to {} successfully....", buyer.email);
         LOG.info("Sent message to {} successfully....", buyer.email);
      } catch (final MessagingException | UnsupportedEncodingException mex) {
         LOG.error("Error sending mail", mex);
      }

   }

   private void setIfMissing(final String key, final String defaultValue) {
      if (getProperties().containsKey(key) == false)
         setProperty(key, defaultValue);
   }

   private MimeMessage createMessage(final SantaRecord buyer, final SantaRecord receiver, final Session session, final String sender) throws MessagingException, UnsupportedEncodingException {
      final MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(sender));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(buyer.email));
      message.setSubject(getPropertyOrDie("email.subject"));
      message.setText(constructMessageText(buyer, receiver));
      return message;
   }

}
