package com.akka.mailbox;

import akka.actor.AbstractActor;

/**
 * Created by ssendrayan on 02-08-2017.
 */
public class Actor extends AbstractActor {
    @Override
    public void postStop(){
        System.out.println("Actor has been stopped");
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(msg->{
                    System.out.println(msg);
                   // Thread.sleep(2000);
                ;})
                .build();
    }
}
