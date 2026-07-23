package BehaviouralPattern.ObserverPattern;

import java.util.*;

//======================================================================
// ENTITY
//======================================================================

class Subscriber {

    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void notifyUser(String videoTitle) {

        System.out.println(
                name + " received notification : " + videoTitle
        );
    }
}



//======================================================================
// NORMAL IMPLEMENTATION
//======================================================================

//Suppose we own a YouTube Channel.
//
//Whenever a new video is uploaded,
//every subscriber should receive
//a notification.

class YouTubeChannel {

    private List<Subscriber> subscribers = new ArrayList<>();

    public void addSubscriber(Subscriber subscriber) {

        subscribers.add(subscriber);
    }

    public void uploadVideo(String videoTitle) {

        System.out.println("Uploading : " + videoTitle);

        //--------------------------------------------------
        //Problem
        //--------------------------------------------------

        //Channel is directly notifying
        //every subscriber.

        //Suppose today we have

        //Rahul

        //Priyanshu

        //Aman

        //Tomorrow

        //100

        //1000

        //1 Million Subscribers

        //Still Channel has to notify
        //everyone manually.

        for (Subscriber subscriber : subscribers) {

            subscriber.notifyUser(videoTitle);
        }

        //--------------------------------------------------
        //More Problems
        //--------------------------------------------------

        //Channel is tightly coupled
        //with Subscriber class.

        //Tomorrow if we introduce

        //EmailSubscriber

        //SMSSubscriber

        //PushNotificationSubscriber

        //DiscordSubscriber

        //TelegramSubscriber

        //Channel class needs modification.

        //Violation

        //Open Closed Principle

        //Channel should only upload video.

        //Notification logic should not
        //be Channel's responsibility.

        //We need loose coupling.

        //This is where
        //Observer Pattern comes into picture.
    }
}



//======================================================================
// CLIENT
//======================================================================

public class A_NormalImplementation {

    public static void main(String[] args) {

        YouTubeChannel channel = new YouTubeChannel();

        channel.addSubscriber(
                new Subscriber("Priyanshu"));

        channel.addSubscriber(
                new Subscriber("Rahul"));

        channel.addSubscriber(
                new Subscriber("Aman"));

        channel.uploadVideo("Iterator Pattern Explained");
    }
}
//Initially,
//
//my YouTubeChannel directly stored all Subscribers.
//
//Whenever a new video was uploaded,
//it manually notified every subscriber.
//
//Although it works,
//
//the Channel became tightly coupled
//with Subscriber.
//
//Tomorrow if notification mechanisms change,
//
//like Email,
//
//SMS,
//
//Push,
//
//Discord,
//
//Telegram,
//
//I would have to modify the Channel class.
//
//That violates Open Closed Principle
//and Single Responsibility Principle.
//
//So instead of depending on Subscriber,
//
//I introduced an Observer interface,
//which decouples the Channel
//from concrete subscribers.