package com.evershall.secretsanta.data;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SantaRecord {

   public String name;
   public String salutation;
   public String email;

   public SantaRecord(final CSVRecord record) {
      this.name = record.get("name");
      this.salutation = record.get("salutation");
      this.email = record.get("email");
   }

   public SantaRecord(final String name, final String salutation, final String email) {
      this.name = name;
      this.salutation = salutation;
      this.email = email;
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
