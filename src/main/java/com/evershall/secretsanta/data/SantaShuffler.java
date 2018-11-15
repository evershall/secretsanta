package com.evershall.secretsanta.data;

import org.slf4j.Logger;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

public class SantaShuffler {

   private static final Logger LOG = getLogger(SantaShuffler.class);

   public void shuffle(final List<SantaRecord> santaRecords) {
      LOG.debug("run : records pre shuffle : {}", santaRecords);
      Collections.shuffle(santaRecords);
      LOG.debug("run : records post shuffle : {}", santaRecords);
   }

}
