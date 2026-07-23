//यय. Ye AdvancedObserverPattern.java hai. Isme hum dikhate hain ki naye
//        Observer add karne par YouTubeChannel me ek line bhi change nahi hoti, jo Observer Pattern ka sabse bada advantage hai.
package BehaviouralPattern.ObserverPattern;
import java.util.*;

//======================================================================
// OBSERVER
//======================================================================

//Every notification service
//should implement Observer.
//
//Channel should never know
//whether notification is sent
//through Email,
//SMS,
//Push Notification,
//Discord,
//Telegram,
//etc.

interface Observer {

    void update(String videoTitle);
}



//======================================================================
// CONCRETE OBSERVERS
//======================================================================

class EmailSubscriber implements Observer {

    private String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(String videoTitle) {

        System.out.println(
                "📧 Email sent to "
                        + email
                        + " : "
                        + videoTitle
        );
    }
}



class SMSSubscriber implements Observer {

    private String phone;

    public SMSSubscriber(String phone) {
        this.phone = phone;
    }

    @Override
    public void update(String videoTitle) {

        System.out.println(
                "📱 SMS sent to "
                        + phone
                        + " : "
                        + videoTitle
        );
    }
}



class PushNotificationSubscriber implements Observer {

    private String username;

    public PushNotificationSubscriber(String username) {
        this.username = username;
    }

    @Override
    public void update(String videoTitle) {

        System.out.println(
                "🔔 Push Notification sent to "
                        + username
                        + " : "
                        + videoTitle
        );
    }
}



class DiscordSubscriber implements Observer {

    private String username;

    public DiscordSubscriber(String username) {
        this.username = username;
    }

    @Override
    public void update(String videoTitle) {

        System.out.println(
                "🎮 Discord Notification sent to "
                        + username
                        + " : "
                        + videoTitle
        );
    }
}



//======================================================================
// SUBJECT
//======================================================================

interface Subject {

    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void notifyObservers();
}



//======================================================================
// CONCRETE SUBJECT
//======================================================================

//Notice carefully.
//
//Channel knows NOTHING
//about Email,
//SMS,
//Discord,
//Push Notification.
//
//It only knows
//Observer interface.
//
//Tomorrow if we introduce
//
//WhatsAppSubscriber
//
//SlackSubscriber
//
//TelegramSubscriber
//
//We won't modify this class.
//
//This follows
//Open Closed Principle.

class YouTubeChannel implements Subject {

    private List<Observer> observers =
            new ArrayList<>();

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

        for (Observer observer : observers) {

            observer.update(latestVideo);
        }
    }

    public void uploadVideo(String title) {

        latestVideo = title;

        System.out.println();
        System.out.println("================================");
        System.out.println("Uploading : " + title);
        System.out.println("================================");

        notifyObservers();
    }
}



//======================================================================
// CLIENT
//======================================================================

public class C_AdvancedObserverPattern {

    public static void main(String[] args) {

        YouTubeChannel channel =
                new YouTubeChannel();

        //--------------------------------------------------
        //Different Observer Types
        //--------------------------------------------------

        Observer email =
                new EmailSubscriber("priyanshu@gmail.com");

        Observer sms =
                new SMSSubscriber("+91-9876543210");

        Observer push =
                new PushNotificationSubscriber("Priyanshu");

        Observer discord =
                new DiscordSubscriber("Priyanshu#1234");



        //--------------------------------------------------
        //Subscribe
        //--------------------------------------------------

        channel.subscribe(email);
        channel.subscribe(sms);
        channel.subscribe(push);
        channel.subscribe(discord);



        //--------------------------------------------------
        //Video Upload
        //--------------------------------------------------

        channel.uploadVideo(
                "Observer Pattern Explained");



        //--------------------------------------------------
        //SMS User unsubscribes
        //--------------------------------------------------

        channel.unsubscribe(sms);



        channel.uploadVideo(
                "Strategy Pattern Explained");
    }
}