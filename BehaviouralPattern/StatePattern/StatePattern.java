import java.util.*;

// ============================
// State Pattern (Behavioral Design Pattern)
// Allows an object to change its behavior when
// its internal state changes.
// ============================

interface OrderState {

    void next(Order order);

    void cancel(Order order);

    String getStatus();
}

class OrderPlacedState implements OrderState {

    @Override
    public void next(Order order) {
        order.setState(new PreparingState());
    }

    @Override
    public void cancel(Order order) {
        order.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }

    @Override
    public String getStatus() {
        return "ORDER_PLACED";
    }
}

class PreparingState implements OrderState {

    @Override
    public void next(Order order) {
        order.setState(new OutForDeliveryState());
    }

    @Override
    public void cancel(Order order) {
        order.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }

    @Override
    public String getStatus() {
        return "PREPARING";
    }
}

class OutForDeliveryState implements OrderState {

    @Override
    public void next(Order order) {
        order.setState(new DeliveredState());
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Cannot cancel the order now.");
    }

    @Override
    public String getStatus() {
        return "OUT_FOR_DELIVERY";
    }
}

class DeliveredState implements OrderState {

    @Override
    public void next(Order order) {
        System.out.println("Order already delivered.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Cannot cancel the order now.");
    }

    @Override
    public String getStatus() {
        return "DELIVERED";
    }
}

class CancelledState implements OrderState {

    @Override
    public void next(Order order) {
        System.out.println("Cancelled order cannot move further.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Order already cancelled.");
    }

    @Override
    public String getStatus() {
        return "CANCELLED";
    }
}

class Order {

    private OrderState state;

    public Order() {
        state = new OrderPlacedState();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void nextState() {
        state.next(this);
    }

    public void cancelOrder() {
        state.cancel(this);
    }

    public void printStatus() {
        System.out.println("Current State: " + state.getStatus());
    }
}

public class Main {

    public static void main(String[] args) {

        Order order = new Order();

        order.printStatus();

        order.nextState();
        order.printStatus();

        order.nextState();
        order.printStatus();

        order.cancelOrder();

        order.nextState();
        order.printStatus();
    }
}