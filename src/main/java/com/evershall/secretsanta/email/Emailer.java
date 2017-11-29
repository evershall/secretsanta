package com.evershall.secretsanta.email;

import com.evershall.secretsanta.data.SantaRecord;

public interface Emailer {

   void send(SantaRecord buyer, SantaRecord receiver);

}
