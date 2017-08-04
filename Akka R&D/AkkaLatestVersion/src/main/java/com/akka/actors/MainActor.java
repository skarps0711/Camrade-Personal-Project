package com.akka.actors;

import akka.Main;
import akka.actor.*;
import akka.japi.pf.ReceiveBuilder;


public class MainActor extends AbstractActor {
    @Override
    public void preStart(){
        System.out.println("Inside preStart method");
    }


    public MainActor(){
        getContext().watch(child1);
        getContext().watch(child2);
        getContext().watch(child3);
    }
    ActorRef child1=getContext().actorOf(Props.create(ChilaActor1.class),"child1");
    ActorRef child2=getContext().actorOf(Props.create(ChildActor2.class),"child2");
    ActorRef child3=getContext().actorOf(Props.create(ChildActor3.class),"child3");

    @Override
    public Receive createReceive() {
        ReceiveBuilder builder = ReceiveBuilder.create();
        builder.match(String.class, s -> {

            if(s.equalsIgnoreCase("createChild1")){
                child1.tell("child 1 created",getSelf());
              /* for(int i=1;i<=200;i++){
                    child1.tell(i,getSelf());
                }
               // getContext().stop(child1);
                  child1.tell(PoisonPill.getInstance(),getSelf());
                for(int i=200;i>=1;i--){
                    child1.tell(i,getSelf());
                }*/
            }
            if(s.equalsIgnoreCase("createChild2")){
                child2.tell("child 2 created",getSelf());
            }
            if(s.equalsIgnoreCase("createChild3")){
              //  System.out.println("child 3 creating");
                child3.tell("Child 3 created",getSelf());
              /*  child3.tell("become1",getSelf());
                child3.tell("test",getSelf());
                child3.tell("test",getSelf());
                child3.tell("become2",getSelf());
                child3.tell("test",getSelf());
                child3.tell("test",getSelf());*/
            }
            if(s.equalsIgnoreCase("stopActor")){
                getContext().stop(child1);
            }
            if(s.equalsIgnoreCase("stopChild1")){
                System.out.println("Stopping child 1");
                getContext().stop(child1);
            }
            if(s.equalsIgnoreCase("stopChild2")){
                getContext().stop(child2);
            }if(s.equalsIgnoreCase("stopChild3")){
                getContext().stop(child3);
            }

        });
        builder.match(Integer.class,i->{
            System.out.println("Integer value : "+i);
            getSender().tell("reply from actor",getSelf());
        });
        builder.match(Terminated.class,msg->{
           System.out.println("Child actors termination notification to parent : " + msg);
        });
        builder.matchAny(s->{
            System.out.println("Don't set match for "+s);
        });
        return builder.build();
    }

    public void postStop(){
        System.out.println("Main actor stopped (PostStop)");
    }
}
