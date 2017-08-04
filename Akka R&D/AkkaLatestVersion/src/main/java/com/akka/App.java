package com.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.routing.*;
import com.akka.mailbox.Actor;
import com.akka.mailbox.controlawaremailbox.MyControlAware;
import com.akka.routing.RoutingActor;

import static akka.pattern.PatternsCS.ask;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("firstActor");
        // TEST ACTORS
        /*
        ActorRef actor = system.actorOf(Props.create(MainActor.class), "mainActor");
        actor.tell("createChild1", actor);
        actor.tell("createChild2", actor);
        actor.tell("createChild3", actor);
      //  actor.tell("stopActor",actor);
     //   actor.tell("stopChild1", actor);
      // test dispatchers
*//*       ActorRef dispatch1=system.actorOf(Props.create(DispatcherTestActor1.class).withDispatcher("test-thread-pool-executor"),"dispatch1");
        ActorRef dispatch2=system.actorOf(Props.create(DispatcherTestActor2.class).withDispatcher("test-thread-pool-executor"),"dispatch2");
        ActorRef dispatch3=system.actorOf(Props.create(DispatcherTestActor3.class).withDispatcher("test-thread-pool-executor"),"dispatch3");
        ActorRef dispatch4=system.actorOf(Props.create(DispatcherTestActor4.class).withDispatcher("test-thread-pool-executor"),"dispatch4");

        for(int i=0;i<10;i++){
            try{
                dispatch1.tell(i,ActorRef.noSender());
                dispatch1.tell(i,ActorRef.noSender());
                dispatch1.tell(i,ActorRef.noSender());
                dispatch1.tell(i,ActorRef.noSender());
              //  dispatch2.tell(i,ActorRef.noSender());
             //   dispatch3.tell(i,ActorRef.noSender());
              //  dispatch4.tell(i,ActorRef.noSender());

            }catch (Exception e){
                e.printStackTrace();
            }
        }*//*
        // TEST ACTORS ENDS HERE
*/

        // TEST MAILBOXES
/*

            // TEST PRIORITY MAILBOX
        ActorRef actor=system.actorOf(Props.create(Actor.class).withMailbox("prio-mailbox"),"actor");
        actor.tell("lowpriority",actor);
        actor.tell("lowpriority",actor);
        actor.tell("highpriority",actor);
        actor.tell("3",actor);
        actor.tell("2",actor);
        actor.tell("1",actor);
        actor.tell(PoisonPill.getInstance(),actor);
        actor.tell("highpriority",actor);
        actor.tell("highpriority",actor);
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        actor.tell("highpriority",actor);
            // TEST PRIORITY MAILBOX ENDS HERE


            // TEST CONTROL AWARE MAILBOX
        ActorRef actor2=system.actorOf(Props.create(Actor.class).withMailbox("control-aware-mailbox"),"actor");
        MyControlAware control=new MyControlAware();
        actor2.tell("message 1",actor);
        actor2.tell("message 2",actor);
        actor2.tell("message 3",actor);
        actor2.tell("message 4",actor);
        actor2.tell("message 5",actor);
        actor2.tell(new MyControlAware(),actor);
            // TEST CONTROL AWARE MAILBOX ENDS HERE


            // TEST BOUNDED MAILBOX

        ActorRef actor3=system.actorOf(Props.create(Actor.class).withMailbox("bounded-mailbox"),"actor");
        actor.tell("message 1",actor);
        for(int i=2;i<20;i++){
            actor3.tell("message "+i,actor);
        }


            // TEST BOUNDED MAILBOX ENDS HERE
*/
        // TEST MAILBOXES ENDS HERE

        // --- TEST ROUTING ---

        // Round Robin
        /*ActorRef routingActor=system.actorOf(new RoundRobinPool(10).props(Props.create(RoutingActor.class) ),"actor");
        for(int i=0;i<10;i++){
            routingActor.tell(i,ActorRef.noSender());
        }*/

        // Balancing pool
       /* ActorRef balancingPool=system.actorOf(new BalancingPool(3).props(Props.create(RoutingActor.class) ),"actor");
        for(int i=0;i<10;i++){
            balancingPool.tell(i,ActorRef.noSender());
        }*/

        // Broadcast pool
       /* ActorRef broadcastPool = system.actorOf(new BroadcastPool(3).props(Props.create(RoutingActor.class)), "actor");
        for (int i = 0; i < 5; i++) {
            broadcastPool.tell("Message " + i, ActorRef.noSender());
        }*/

        // Smallest mailbox pool
       /* ActorRef smallestMailboxPool = system.actorOf(new SmallestMailboxPool(3).props(Props.create(RoutingActor.class)), "actor");
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                smallestMailboxPool.tell(i, ActorRef.noSender());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // Dynamically resizable pool with Smallest mailbox pool
        // ActorRef roundRobin = system.actorOf(new RoundRobinPool(3).props(Props.create(RoutingActor.class)), "actor");
        ActorRef roundRobin = system.actorOf(FromConfig.getInstance().props(Props.create(RoutingActor.class)), "round-robin-resizer");
        for (int i = 0; i < 15; i++) {
            roundRobin.tell(i, ActorRef.noSender());
        }
    }
}
