package com.evershall.secretsanta;

import com.evershall.secretsanta.email.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.junit.MatcherAssert.assertThat;

class SecretSantaTest {

   @Test
   void whenCreateNewRunner_thenItIsNotNull() throws Exception {
      SecretSanta secretSanta = new SecretSanta();
      assertThat(secretSanta, is(notNullValue()));
   }

   @Test
   void whenCreateNewRunnerWithoutProdFlag_thenEmailerIsDummy() throws Exception {
      SecretSanta secretSanta = new SecretSanta();
      assertThat(secretSanta, is(notNullValue()));
      assertThat(getEmailerImpl(secretSanta), instanceOf(EmailerDummy.class));
   }

   @Test
   void whenCreateNewRunnerWithProdFlag_thenEmailerIsRealImpl() throws Exception {
      System.setProperty("PROD", "true");
      SecretSanta secretSanta = new SecretSanta();
      assertThat(secretSanta, is(notNullValue()));
      assertThat(getEmailerImpl(secretSanta), instanceOf(EmailerImpl.class));
      System.clearProperty("PROD");
   }

   private Emailer getEmailerImpl(SecretSanta secretSanta) throws NoSuchFieldException, IllegalAccessException {
      Field f = secretSanta.getClass().getDeclaredField("emailerImpl");
      f.setAccessible(true);
      return (Emailer) f.get(secretSanta);
   }

   //   @Test
   //   void whenCallRun_then() {
   //      System.setProperty() SecretSanta secretSanta = new SecretSanta();
   //      assertThat(secretSanta, is(notNullValue()));
   //   }

}