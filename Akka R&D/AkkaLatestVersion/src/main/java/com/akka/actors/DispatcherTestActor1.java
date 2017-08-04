package com.akka.actors;

import akka.actor.AbstractActor;
import scala.concurrent.ExecutionContext;

/**
 * Created by ssendrayan on 01-08-2017.
 */
public class DispatcherTestActor1 extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Integer.class,i->{
                    System.out.println("Dispatcher 1 thread is : "+Thread.currentThread().getId()+" value = "+i);
                })
                .matchAny(a->{}).build();
    }
}
