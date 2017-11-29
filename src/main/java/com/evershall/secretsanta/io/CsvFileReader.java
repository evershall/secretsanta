package com.evershall.secretsanta.io;

import com.evershall.secretsanta.data.*;
import org.apache.commons.csv.*;
import org.slf4j.Logger;

import java.io.*;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.slf4j.LoggerFactory.getLogger;

public class CsvFileReader {

   private static final Logger LOG = getLogger(CsvFileReader.class);
   private final SantaRecordConverter converter = new SantaRecordConverter();

   public List<SantaRecord> read(String filename) {
      try {
         return readCsvFile(filename);
      }
      catch (IOException e) {
         LOG.error("Unable to read file ({})", filename, e);
         return emptyList();
      }
   }

   private List<SantaRecord> readCsvFile(String filename) throws IOException {

      Reader in = new FileReader(filename);
      Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
      List<SantaRecord> santaRecords = converter.convert(records);
      LOG.info("read in {} records from {}", santaRecords.size(), filename);
      LOG.debug("records : {}", santaRecords);

      return santaRecords;
   }

}
