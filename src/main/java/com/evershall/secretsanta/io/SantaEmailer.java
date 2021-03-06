package com.evershall.secretsanta.io;

import com.evershall.secretsanta.data.SantaRecord;
import com.evershall.secretsanta.email.Emailer;
import org.slf4j.Logger;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class SantaEmailer {

   private static final Logger LOG = getLogger(SantaEmailer.class);
   private final Emailer emailImpl;

   public SantaEmailer(final Emailer emailImpl) {
      this.emailImpl = emailImpl;
   }

   public void sendEmails(final List<SantaRecord> santaRecords) {

      // now that the records are shuffled, get #0 to buy for #1, #1 to buy for #2, #n-1 to buy for #n, #n to buy for #0

      for (int i = 0; i < santaRecords.size(); i++) {
         final SantaRecord buyer = santaRecords.get(i);
         final SantaRecord receiver = i == santaRecords.size() - 1 ? santaRecords.get(0) : santaRecords.get(i + 1);
         sendEmail(buyer, receiver);
      }

      LOG.info("Successfully sent {} emails", santaRecords.size());
   }

   private void sendEmail(final SantaRecord buyer, final SantaRecord receiver) {
      LOG.debug("{} is buying for {}", buyer.name, receiver.name);
      emailImpl.send(buyer, receiver);
   }

}
