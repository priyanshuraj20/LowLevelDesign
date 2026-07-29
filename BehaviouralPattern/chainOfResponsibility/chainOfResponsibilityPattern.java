package BehaviouralPattern.chainOfResponsibility;

import java.util.*;

// ===========================================
// Chain of Responsibility Pattern
// (Behavioral Design Pattern)
//
// Each handler decides whether it can handle
// the request. If not, it forwards the request
// to the next handler in the chain.
// ===========================================

abstract class SupportHandler {

    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String requestType);
}

class GeneralSupport extends SupportHandler {

    @Override
    public void handleRequest(String requestType) {

        if (requestType.equalsIgnoreCase("general")) {
            System.out.println("GeneralSupport: Handling general query");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

class BillingSupport extends SupportHandler {

    @Override
    public void handleRequest(String requestType) {

        if (requestType.equalsIgnoreCase("refund")) {
            System.out.println("BillingSupport: Handling refund request");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

class TechnicalSupport extends SupportHandler {

    @Override
    public void handleRequest(String requestType) {

        if (requestType.equalsIgnoreCase("technical")) {
            System.out.println("TechnicalSupport: Handling technical issue");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

class DeliverySupport extends SupportHandler {

    @Override
    public void handleRequest(String requestType) {

        if (requestType.equalsIgnoreCase("delivery")) {
            System.out.println("DeliverySupport: Handling delivery complaint");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        } else {
            System.out.println("DeliverySupport: No handler found for request");
        }
    }
}

public class chainOfResponsibilityPattern {

    public static void main(String[] args) {

        SupportHandler general = new GeneralSupport();
        SupportHandler billing = new BillingSupport();
        SupportHandler technical = new TechnicalSupport();
        SupportHandler delivery = new DeliverySupport();

        // Setting up chain:
        // general -> billing -> technical -> delivery
        general.setNextHandler(billing);
        billing.setNextHandler(technical);
        technical.setNextHandler(delivery);

        general.handleRequest("general");
        general.handleRequest("refund");
        general.handleRequest("technical");
        general.handleRequest("delivery");
        general.handleRequest("unknown");
    }
}
