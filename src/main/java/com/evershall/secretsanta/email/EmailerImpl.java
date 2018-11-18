package com.evershall.secretsanta.email;

import com.evershall.secretsanta.data.SantaRecord;
import org.slf4j.Logger;

import javax.mail.*;
import javax.mail.internet.*;

import static com.evershall.secretsanta.email.EmailBodyFactory.constructMessageText;
import static com.evershall.secretsanta.email.PropertyHelper.getPropertyOrDie;
import static java.lang.System.*;
import static org.slf4j.LoggerFactory.getLogger;

public class EmailerImpl implements Emailer {

   private static final Logger LOG = getLogger(EmailerImpl.class);

   @Override
   public void send(final SantaRecord buyer, final SantaRecord receiver) {

      try {
         if (getProperties().containsKey("mail.smtp.host") == false)
            setProperty("mail.smtp.host", "localhost");

         final MimeMessage message = createMessage(buyer, receiver, Session.getDefaultInstance(getProperties()));
         Transport.send(message);
         LOG.debug("Sent message to {} successfully....", buyer.email);
      } catch (final MessagingException mex) {
         mex.printStackTrace();
      }

   }

   private MimeMessage createMessage(final SantaRecord buyer, final SantaRecord receiver, final Session session) throws MessagingException {
      final MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(buyer.email));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(buyer.email));
      message.setSubject(getPropertyOrDie("email.subject"));
      message.setText(constructMessageText(buyer, receiver));
      return message;
   }

}
