package com.lazytomatostudios.svceinterrupt.interfaces;

/**
 * Created by drpayyne on 12/9/17.
 */

public interface MailInterface {

    public void getMail(String mail);

    public String sendMail();

    public void storePass(String pass);

    public String getPass();

}
