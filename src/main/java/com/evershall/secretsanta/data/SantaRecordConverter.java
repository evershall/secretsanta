package com.evershall.secretsanta.data;

import org.apache.commons.csv.CSVRecord;

import java.util.*;

public class SantaRecordConverter {

   public List<SantaRecord> convert(Iterable<CSVRecord> records) {

      List<SantaRecord> retval = new ArrayList<>();
      records.forEach(r -> retval.add(new SantaRecord(r)));
      return retval;
   }

}
