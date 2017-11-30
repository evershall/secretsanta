package com.evershall.secretsanta.io;

import com.evershall.secretsanta.data.SantaRecord;
import com.evershall.secretsanta.email.Emailer;
import org.junit.jupiter.api.*;

import java.util.*;

import static java.util.Collections.emptyList;
import static org.easymock.EasyMock.*;

class SantaEmailerTest {

   private SantaEmailer santaEmailer;
   private Emailer mockEmailer = createStrictMock(Emailer.class);

   @BeforeEach
   void setUp() {
      santaEmailer = new SantaEmailer(mockEmailer);
   }

   @Test
   void whenNoRecords_thenDoNotSendAnything() {
      replay(mockEmailer);
      santaEmailer.sendEmails(emptyList());
      verify(mockEmailer);
   }

   @Test
   void whenOneRecord_thenSendOneEmail() {

      List<SantaRecord> santaRecords = createList(1);

      mockEmailer.send(santaRecords.get(0), santaRecords.get(0));

      replay(mockEmailer);
      santaEmailer.sendEmails(santaRecords);
      verify(mockEmailer);
   }

   @Test
   void whenMultipleRecord_thenSendAllEmails() {

      List<SantaRecord> santaRecords = createList(3);

      mockEmailer.send(santaRecords.get(0), santaRecords.get(1));
      mockEmailer.send(santaRecords.get(1), santaRecords.get(2));
      mockEmailer.send(santaRecords.get(2), santaRecords.get(0));

      replay(mockEmailer);
      santaEmailer.sendEmails(santaRecords);
      verify(mockEmailer);
   }

   private List<SantaRecord> createList(int numRecords) {

      List<SantaRecord> retval = new ArrayList<>();

      for (int i = 1; i <= numRecords; i++)
         retval.add(new SantaRecord("" + i, "" + i, "" + i));

      return retval;

   }
}