package com.akka.actors;

import akka.actor.AbstractActor;

/**
 * Created by ssendrayan on 31-07-2017.
 */
public class ChilaActor1 extends AbstractActor {
    @Override
    public void preStart() {
        System.out.println("Inside child 1 preStart method");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(msg -> {
                    System.out.println(msg+" "+Thread.currentThread().getId()+" path : "+getSelf());
                })
                .build();
    }

    public void postStop() {
        System.out.println("Child 1 stopped (PostStop)");
    }
}
