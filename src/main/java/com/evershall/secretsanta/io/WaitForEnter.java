package com.evershall.secretsanta.io;

import java.io.Console;

public class WaitForEnter {

   public static void waitForEnter(String message, Object... args) {

      Console c = System.console();

      if (c != null) {
         if (message != null)
            c.format(message, args);
         else
            c.format("\nPress ENTER to proceed.\n");

         c.readLine();
      }
   }

}
