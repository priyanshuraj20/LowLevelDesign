package BehaviouralPattern.MediatorPattern;

import java.util.*;

// ============================================================
// Mediator Pattern (Behavioral Design Pattern)
//
// Definition:
//
// Instead of objects communicating directly,
// all communication happens through a Mediator.
//
// Objects only know the Mediator.
//
// They DO NOT know each other.
//
// Real Life:
//
// WhatsApp Server
// Air Traffic Control
// Chat Room
// ============================================================



// ============================================================
// MEDIATOR
//
// Defines communication between users.
// ============================================================

interface ChatMediator {

    void sendMessage(String message, User sender);

    void addUser(User user);
}



// ============================================================
// CONCRETE MEDIATOR
//
// Maintains all users.
//
// Responsible for delivering messages.
//
// Users never communicate directly.
// ============================================================

class ChatRoom implements ChatMediator {

    private List<User> users = new ArrayList<>();


    // Register a new user in chat room
    @Override
    public void addUser(User user) {

        users.add(user);
    }


    // Send message to everyone except sender
    @Override
    public void sendMessage(String message, User sender) {

        for (User user : users) {

            // Don't send back to sender
            if (user != sender) {

                user.receive(message, sender.getName());
            }
        }
    }
}



// ============================================================
// COLLEAGUE
//
// Every user knows ONLY the Mediator.
//
// User never knows other users.
// ============================================================

class User {

    private String name;

    private ChatMediator mediator;


    public User(String name, ChatMediator mediator) {

        this.name = name;
        this.mediator = mediator;
    }


    public String getName() {

        return name;
    }


    // User sends message

    public void send(String message) {

        System.out.println(name + " sends : " + message);

        mediator.sendMessage(message, this);
    }


    // Receive message

    public void receive(String message, String sender) {

        System.out.println(name + " received from " + sender + " : " + message);
    }
}



// ============================================================
// MAIN
// ============================================================

public class MediatorPattern {

    public static void main(String[] args) {

        ChatMediator chatRoom = new ChatRoom();


        User alice = new User("Alice", chatRoom);
        User bob = new User("Bob", chatRoom);
        User charlie = new User("Charlie", chatRoom);


        // Register users
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);


        System.out.println();

        alice.send("Hello Everyone!");

        System.out.println();

        bob.send("Hi Alice!");

    }
}


/*

====================== FLOW ======================

Alice.send()

        ↓

ChatRoom.sendMessage()

        ↓

Loop through all registered users

        ↓

Bob.receive()

Charlie.receive()




====================== IMPORTANT ======================

Notice

Alice NEVER knows Bob.

Alice NEVER knows Charlie.

Alice only knows ChatRoom.

Similarly

Bob only knows ChatRoom.

Charlie only knows ChatRoom.




====================== ADVANTAGES ======================

✔ Loose Coupling

Users don't depend on each other.


✔ Centralized Communication

All communication logic is inside ChatRoom.


✔ Easy to Add Users

Simply register new user.


✔ Cleaner Code

Users only send/receive messages.

Mediator handles routing.




====================== INTERVIEW ======================

Without Mediator

Alice ----> Bob
Alice ----> Charlie
Alice ----> David

Bob ----> Alice
Bob ----> Charlie

Many Dependencies


With Mediator

Alice

↓

ChatRoom

↓

Bob

↓

Charlie


Only ONE dependency.


Examples

✔ Chat Application

✔ WhatsApp Server

✔ Air Traffic Control

✔ Smart Home

✔ Auction System

✔ GUI Dialog Box

*/