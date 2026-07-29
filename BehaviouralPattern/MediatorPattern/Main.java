package BehaviouralPattern.MediatorPattern;

import java.util.*;

// ============================================================
// Scenario:
//
// We are building a Chat Application.
//
// There are 3 users:
//
// Alice
// Bob
// Charlie
//
// Every user directly communicates with every other user.
//
// This creates a lot of dependencies.
// ============================================================

class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    // Send message directly to another user
    public void sendMessage(User receiver, String message) {

        System.out.println(name + " sends to " + receiver.name + " : " + message);

        receiver.receiveMessage(name, message);
    }

    // Receive message
    public void receiveMessage(String sender, String message) {

        System.out.println(name + " received from " + sender + " : " + message);
    }
}

public class Main {

    public static void main(String[] args) {

        User alice = new User("Alice");
        User bob = new User("Bob");
        User charlie = new User("Charlie");

        // Alice directly knows Bob
        alice.sendMessage(bob, "Hi Bob!");

        System.out.println();

        // Alice directly knows Charlie
        alice.sendMessage(charlie, "Hi Charlie!");

        System.out.println();

        // Bob directly knows Charlie
        bob.sendMessage(charlie, "Hello Charlie!");
    }
}

/*

Problems

Suppose tomorrow we have

Alice
Bob
Charlie
David
Eva
John
Mike
...

Now every user must know every other user.

Alice ----> Bob
Alice ----> Charlie
Alice ----> David
Alice ----> Eva

Bob ----> Alice
Bob ----> Charlie
Bob ----> David

....

This becomes Tight Coupling.

Every object depends on every other object.

Adding new users becomes difficult.

Communication logic is scattered across users.

Violates Single Responsibility Principle.

*/


//Interview Difference (Naive vs Mediator)
//Naive   	Mediator
//Objects talk directly	       Objects communicate through Mediator
//Tight Coupling	    Loose Coupling
//Every object knows others    	Objects know only Mediator
//Communication logic scattered    	Communication centralized
//Hard to maintain	       Easy to maintain
//O(n²) dependencies	O(n) dependencies