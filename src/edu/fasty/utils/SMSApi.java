package edu.fasty.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Mahdi
 */
public class SMSApi {

    public SMSApi() {
    }
    public static final String ACCOUNT_SID = "AC81b3aee9ea1d2c625ffd60b6c8bdebdd";
    public static final String AUTH_TOKEN = "5d932086e1f173bc1fe5bfa62fcbd340";

    public void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+21651089470"),
                new PhoneNumber("+15673716092"),
                "Votre nouveau Evenement est ajouté avec succée, " + msg).create();

        System.out.println(message.getSid());

    }
}
