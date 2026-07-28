import java.util.*;

class Order {

    private String state;

    public Order() {
        this.state = "ORDER_PLACED";
    }

    public void cancelOrder() {
        if (state.equals("ORDER_PLACED") || state.equals("PREPARING")) {
            state = "CANCELLED";
            System.out.println("Order has been cancelled.");
        } else {
            System.out.println("Cannot cancel the order now.");
        }
    }

    public void nextState() {
        switch (state) {
            case "ORDER_PLACED":
                state = "PREPARING";
                break;

            case "PREPARING":
                state = "OUT_FOR_DELIVERY";
                break;

            case "OUT_FOR_DELIVERY":
                state = "DELIVERED";
                break;

            default:
                System.out.println("No next state from: " + state);
        }
    }

    public void printStatus() {
        System.out.println("Current State: " + state);
    }
}

public class Normal {

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