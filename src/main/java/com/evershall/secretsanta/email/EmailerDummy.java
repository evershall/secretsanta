package com.evershall.secretsanta.email;

import com.evershall.secretsanta.data.SantaRecord;
import org.slf4j.Logger;

import static com.evershall.secretsanta.email.EmailBodyFactory.constructMessageText;
import static org.slf4j.LoggerFactory.getLogger;

public class EmailerDummy implements Emailer {

   private static final Logger LOG = getLogger(EmailerDummy.class);

   @Override
   public void send(final SantaRecord buyer, final SantaRecord receiver) {
      LOG.info(constructMessageText(buyer, receiver));
   }

}
