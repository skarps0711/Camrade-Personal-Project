package com.akka.mailbox.controlawaremailbox;

import akka.dispatch.ControlMessage;

/**
 * Created by ssendrayan on 02-08-2017.
 */
public class MyControlAware implements ControlMessage {

    public MyControlAware() {
    }

    public String toString(){
        return "message from control message";
    }
}
