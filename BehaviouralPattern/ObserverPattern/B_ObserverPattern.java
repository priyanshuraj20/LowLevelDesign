package BehaviouralPattern.ObserverPattern;

import java.util.*;

//======================================================================
// OBSERVER
//======================================================================

//Every subscriber should know
//how to receive notification.
//
//Channel should not care
//what kind of subscriber it is.

interface Observer {

    void update(String videoTitle);
}



//======================================================================
// CONCRETE OBSERVER
//======================================================================

//Concrete implementation
//of Observer.

class Subscriber implements Observer {

    private String name;

    public Subscriber(String name) {

        this.name = name;
    }

    @Override
    public void update(String videoTitle) {

        System.out.println(
                name + " received notification : " + videoTitle
        );
    }
}



//======================================================================
// SUBJECT
//======================================================================

//Every Subject should support
//
//Subscribe
//
//Unsubscribe
//
//Notify Observers

interface Subject {

    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void notifyObservers();
}



//======================================================================
// CONCRETE SUBJECT
//======================================================================

//Channel doesn't know
//which type of observer
//is listening.
//
//It only knows
//Observer interface.

class YouTubeChannel implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private String latestVideo;

    @Override
    public void subscribe(Observer observer) {

        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {

        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {

        //Notify everyone.

        for (Observer observer : observers) {

            observer.update(latestVideo);
        }
    }

    //Business Logic.

    public void uploadVideo(String title) {

        System.out.println("Uploading Video : " + title);

        latestVideo = title;

        //After uploading video,
        //simply notify everyone.

        notifyObservers();
    }
}



//======================================================================
// CLIENT
//======================================================================

//Client creates
//
//Channel
//
//Subscribers
//
//Subscription.
//
//After that,
//Channel doesn't know
//who is listening.

public class B_ObserverPattern {

    public static void main(String[] args) {

        YouTubeChannel channel =
                new YouTubeChannel();

        Observer priyanshu =
                new Subscriber("Priyanshu");

        Observer rahul =
                new Subscriber("Rahul");

        Observer aman =
                new Subscriber("Aman");

        //--------------------------------------------------
        //Subscribe
        //--------------------------------------------------

        channel.subscribe(priyanshu);
        channel.subscribe(rahul);
        channel.subscribe(aman);

        //--------------------------------------------------
        //Upload Video
        //--------------------------------------------------

        channel.uploadVideo(
                "Observer Pattern Explained");

        System.out.println();

        //--------------------------------------------------
        //Rahul Unsubscribes
        //--------------------------------------------------

        channel.unsubscribe(rahul);

        channel.uploadVideo(
                "Strategy Pattern Explained");
    }
}
//Normal Implementation
//
//↓
//
//Channel directly notified every Subscriber.
//
//↓
//
//Channel became tightly coupled
//with Subscriber class.
//
//↓
//
//To remove this dependency,
//
//I introduced an Observer interface.
//
//↓
//
//Every Subscriber implements Observer.
//
//↓
//
//I also introduced a Subject interface
//that defines subscribe(),
//unsubscribe(),
//and notifyObservers().
//
//↓
//
//YouTubeChannel implements Subject
//and stores Observer references
//instead of concrete Subscriber objects.
//
//↓
//
//Whenever a new video is uploaded,
//
//the Channel simply calls
//notifyObservers().
//
//↓
//
//Each Observer receives update().
//
//↓
//
//Now the Channel doesn't know
//how many subscribers exist,
//what type they are,
//or what they do after receiving the notification.
//
//↓
//
//This achieves Loose Coupling,
//Open-Closed Principle,
//and a One-to-Many relationship,
//which is the main intent of the Observer Pattern.