package com.evershall.secretsanta.email;

import com.evershall.secretsanta.data.SantaRecord;

public class EmailBodyFactory {

   private EmailBodyFactory() {
      // hiding
   }

   public static String constructMessageText(final SantaRecord buyer, final SantaRecord receiver) {

      final String emailBody = PropertyHelper.getPropertyOrDie("email.body");

      return emailBody //
            .replaceAll("\\{\\{buyer.salutation}}", buyer.salutation) //
            .replaceAll("\\{\\{buyer.name}}", buyer.name) //
            .replaceAll("\\{\\{receiver.salutation}}", receiver.salutation) //
            .replaceAll("\\{\\{receiver.name}}", receiver.name) //
            ;
   }
}