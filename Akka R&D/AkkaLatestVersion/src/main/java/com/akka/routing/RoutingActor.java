package com.akka.routing;

import akka.actor.AbstractActor;

/**
 * Created by ssendrayan on 03-08-2017.
 */
// actor
public class RoutingActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        /*return receiveBuilder()
                .match(Integer.class, msg -> {
                    if (msg < 2) {
                        Thread.sleep(5000);
                        System.out.println("Message :  " + msg + " Thread id : " + Thread.currentThread().getId());
                    } else {
                        System.out.println("Message :  " + msg + " Thread id : " + Thread.currentThread().getId());
                    }
                })
                .matchAny(msg -> {
                    System.out.println("Message :  " + msg + " Thread id : " + Thread.currentThread().getId());
                }).build();*/

        // Only for Round robin with resizer
        return receiveBuilder()
                .match(Integer.class, msg -> {
                    System.out.println("Message :  " + msg + " Thread id : " + Thread.currentThread().getId());
                    Thread.sleep(5000);
                })
                .matchAny(msg -> {
                    System.out.println("Error Message :  " + msg + " Thread id : " + Thread.currentThread().getId());
                }).build();
    }
}
