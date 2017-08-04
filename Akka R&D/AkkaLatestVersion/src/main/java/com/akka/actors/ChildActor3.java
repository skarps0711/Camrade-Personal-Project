package com.akka.actors;

import akka.actor.AbstractActor;

/**
 * Created by ssendrayan on 31-07-2017.
 */
public class ChildActor3 extends AbstractActor{
    private AbstractActor.Receive become1;
    private AbstractActor.Receive become2;

    @Override
    public void preStart(){
        System.out.println("Inside child 3 preStart method");
    }

    public ChildActor3() {
        become1 =
                receiveBuilder()
                        .matchEquals("test1", s -> {
                            System.out.println("I'm from become 1 , Msg : "+s);
                            getSender().tell("I am already angry?", getSelf());
                        })
                        .matchEquals("test2", s -> {
                            System.out.println("I'm from become 1 , Msg : "+s);
                            getSender().tell("I am already angry?", getSelf());
                        })
                        .matchEquals("become2", s -> {
                            getContext().become(become2);
                        })
                        .build();

       become2  = receiveBuilder()
                .matchEquals("test1", s -> {
                    System.out.println("I'm from become 2 , Msg : "+s);
                })
               .matchEquals("test2", s -> {
                   System.out.println("I'm from become 2 , Msg : "+s);
               })
                .matchEquals("become1", s -> {
                    getContext().become(become1);
                })
                .build();
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("become1", s ->
                        getContext().become(become1)
                )
                .matchEquals("become2", s ->
                        getContext().become(become2)
                ).matchAny(a->System.out.println(a+" "+Thread.currentThread().getId()+" path : "+getSelf()))
                .build();
    }
    public void postStop(){
        System.out.println("Child 3 stopped (PostStop)");
    }
}
