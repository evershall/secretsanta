package com.evershall.secretsanta.email;

class PropertyHelper {

   private PropertyHelper() {
      // hiding
   }

   static String getPropertyOrDie(final String propertyKey) {

      final String propertyValue = System.getProperty(propertyKey);

      if (propertyValue == null || propertyValue.isEmpty())
         throw new IllegalStateException(propertyKey + " needs to be set in email.properties");

      return propertyValue;
   }
}