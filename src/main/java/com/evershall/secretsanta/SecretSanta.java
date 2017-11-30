package com.evershall.secretsanta;

import com.evershall.secretsanta.data.*;
import com.evershall.secretsanta.email.*;
import com.evershall.secretsanta.io.*;
import org.slf4j.Logger;

import java.util.List;

import static com.evershall.secretsanta.io.WaitForEnter.waitForEnter;
import static org.slf4j.LoggerFactory.getLogger;

public class SecretSanta {

   private static final Logger LOG = getLogger(SecretSanta.class);
   private final boolean TESTING = Boolean.getBoolean("TESTING");

   // to allow testing of this application, if there is a VM flag of "TESTING", only log the emails that would be sent
   private Emailer emailerImpl = TESTING ? new EmailerDummy() : new EmailerImpl();
   private final SantaEmailer emailer = new SantaEmailer(emailerImpl);

   private CsvFileReader fileReader = new CsvFileReader();
   private SantaShuffler shuffler = new SantaShuffler();

   public static void main(String[] args) {
      waitForEnter("This will shuffle the names included in the file %s and then email each person with who they should buy for.\nPress ENTER to proceed or Ctrl-C to cancel",
            args[0]);

      LOG.debug("Starting");
      new SecretSanta().run(args[0]);
      LOG.debug("Finished");
   }

   private void run(String filename) {
      List<SantaRecord> santaRecords = fileReader.read(filename);
      shuffler.shuffle(santaRecords);
      emailer.sendEmails(santaRecords);
   }

}
