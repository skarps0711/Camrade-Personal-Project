package com.akka.actors;

import akka.actor.AbstractActor;

/**
 * Created by ssendrayan on 01-08-2017.
 */
public class DispatcherTestActor3 extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Integer.class,i->{
                    System.out.println("Dispatcher 3 thread is : "+Thread.currentThread().getId()+" value = "+i);
                })
                .matchAny(a->{}).build();
    }
}
