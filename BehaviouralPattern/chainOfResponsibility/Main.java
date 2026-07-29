package BehaviouralPattern.chainOfResponsibility;
//This is the naive implementation shown before introducing the Chain of Responsibility (Behavioral Design Pattern).
import java.util.*;

// In a customer support system for an e-commerce platform like Amazon,
// users raise tickets. These tickets could be:
// - General inquiries
// - Refund requests
// - Technical issues
// - Complaints about delivery

class SupportService {

    public void handleRequest(String type) {

        if (type.equals("general")) {
            System.out.println("Handled by General Support");
        }
        else if (type.equals("refund")) {
            System.out.println("Handled by Billing Team");
        }
        else if (type.equals("technical")) {
            System.out.println("Handled by Technical Support");
        }
        else if (type.equals("delivery")) {
            System.out.println("Handled by Delivery Team");
        }
        else {
            System.out.println("No handler available");
        }
    }
}

public class Main {

    public static void main(String[] args) {

        SupportService support = new SupportService();

        support.handleRequest("general");
        support.handleRequest("refund");
        support.handleRequest("technical");
        support.handleRequest("delivery");
        support.handleRequest("other");
    }
}

// Violates Open-Closed Principle:
// Every time a new request type is added, this method must be modified.
//
// Monolithic code:
// All request handling logic is inside a single class.
//
// Not flexible or scalable:
// Cannot change the order of processing without modifying core logic.