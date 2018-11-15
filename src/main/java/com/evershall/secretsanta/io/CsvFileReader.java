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

   public List<SantaRecord> read(final String filename) {
      try {
         return readCsvFile(filename);
      } catch (final IOException e) {
         LOG.error("Unable to read file ({})", filename, e);
         return emptyList();
      }
   }

   private List<SantaRecord> readCsvFile(final String filename) throws IOException {

      final Reader in = new FileReader(filename);
      final Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
      final List<SantaRecord> santaRecords = converter.convert(records);
      LOG.info("read in {} records from {}", santaRecords.size(), filename);
      LOG.debug("records : {}", santaRecords);

      return santaRecords;
   }

}
