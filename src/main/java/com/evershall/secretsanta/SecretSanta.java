package com.evershall.secretsanta;

import com.evershall.secretsanta.data.*;
import com.evershall.secretsanta.email.*;
import com.evershall.secretsanta.io.*;
import org.slf4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

import static com.evershall.secretsanta.io.WaitForEnter.waitForEnter;
import static org.slf4j.LoggerFactory.getLogger;

public class SecretSanta {

   private static final Logger LOG = getLogger(SecretSanta.class);
   private final boolean PROD = Boolean.getBoolean("PROD");

   /*
    to allow testing of this application, the emails will only be sent
    if there is a VM flag of "PROD" present
     */
   private final Emailer emailerImpl = PROD ? new EmailerImpl() : new EmailerDummy();
   private final SantaEmailer emailer = new SantaEmailer(emailerImpl);

   private final CsvFileReader fileReader = new CsvFileReader();
   private final SantaShuffler shuffler = new SantaShuffler();

   public static void main(final String[] args) throws IOException {
      waitForEnter("This will shuffle the names included in the file %s and then email each person with who they should buy for.\nPress ENTER to proceed or Ctrl-C to cancel",
            args[0]);

      LOG.debug("Starting");

      final InputStream propertiesFile = SecretSanta.class.getClassLoader().getResourceAsStream("email.properties");
      if (propertiesFile == null)
         throw new IllegalStateException("cannot load the email.properties file from the classpath");
      System.getProperties().load(new InputStreamReader(propertiesFile, Charset.forName("UTF-8")));

      new SecretSanta().run(args[0]);

      LOG.debug("Finished");
   }

   private void run(final String filename) {
      final List<SantaRecord> santaRecords = fileReader.read(filename);
      shuffler.shuffle(santaRecords);
      emailer.sendEmails(santaRecords);
   }

}
