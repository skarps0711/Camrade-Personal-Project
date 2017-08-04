package com.akka.actors;

import akka.actor.AbstractActor;
import akka.actor.ReceiveTimeout;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by ssendrayan on 31-07-2017.
 */
public class ChildActor2 extends AbstractActor {
    @Override
    public void preStart(){
        System.out.println("Inside child 2 preStart method");
    }

    /*public ChildActor2(){
        getContext().setReceiveTimeout(Duration.create(2, TimeUnit.SECONDS));
    }*/

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ReceiveTimeout.class,r->{
                    System.out.println("Receive timeout exception occured");
                    getContext().setReceiveTimeout(Duration.Undefined());
                })
                .matchAny(msg->{
                    System.out.println(msg+" "+Thread.currentThread().getId()+" path : "+getSelf());
                })
                .build();
    }
    public void postStop(){
        System.out.println("Child 2 stopped (PostStop)");
    }
}
