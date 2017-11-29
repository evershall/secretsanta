package com.evershall.secretsanta.data;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SantaRecord {

   public String name;
   public String salutation;
   public String email;

   public SantaRecord(CSVRecord record) {
      this.name = record.get("name");
      this.salutation = record.get("salutation");
      this.email = record.get("email");
   }

   @Override
   public String toString() {
      return new ToStringBuilder(this) //
            .append("name", name) //
            .append("salutation", salutation) //
            .append("email", email) //
            .toString();
   }
}
