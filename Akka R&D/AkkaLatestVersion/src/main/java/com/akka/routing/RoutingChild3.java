package com.akka.routing;

import akka.actor.AbstractActor;

/**
 * Created by ssendrayan on 04-08-2017.
 */
public class RoutingChild3 extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(msg->{
                    System.out.println(msg);
                })
                .build();
    }
}
