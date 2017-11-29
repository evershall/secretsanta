package com.evershall.secretsanta.email;

import com.evershall.secretsanta.data.SantaRecord;
import org.slf4j.Logger;

import javax.mail.*;
import javax.mail.internet.*;

import static java.lang.System.*;
import static org.slf4j.LoggerFactory.getLogger;

public class EmailerImpl implements Emailer {

   private static final Logger LOG = getLogger(EmailerImpl.class);

   @Override
   public void send(SantaRecord buyer, SantaRecord receiver) {

      try {
         setProperty("mail.smtp.host", "localhost");
         Transport.send(createMessage(buyer, receiver, Session.getDefaultInstance(getProperties())));
         LOG.debug("Sent message to {} successfully....", buyer.email);
      }
      catch (MessagingException mex) {
         mex.printStackTrace();
      }

   }

   private MimeMessage createMessage(SantaRecord buyer, SantaRecord receiver, Session session) throws MessagingException {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(buyer.email));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(buyer.email));
      message.setSubject("shhh - secret santa!");
      message.setText(buyer.salutation + ", you should buy a secret santa present for " + receiver.name + ".\n\n" + "Remember that this should be £10 - £15 maximum." + "\n\n"
            + "Please make sure that you wrap and label the gift and bring it into the office before Fri 15th Dec.");
      return message;
   }

}
