// Common interface that all logistics must implement.
// Client (Service) only depends on this interface.

interface Logistic {

    void send();
}

// Concrete implementation for Road transport.
class Road implements Logistic {

    @Override
    public void send() {
        System.out.println("Sending by Road");
    }
}

// Concrete implementation for Air transport.
class Air implements Logistic {

    @Override
    public void send() {
        System.out.println("Sending by Air");
    }
}

// Factory class responsible for creating objects.
// Client does NOT use 'new Road()' or 'new Air()' directly.
class LogisticFactory {

    // Static method because no Factory object is required.
    public static Logistic getLogistics(String mode) {

        // Return Road object
        if ("road".equalsIgnoreCase(mode)) {
            return new Road();
        }

        // Default: return Air object
        return new Air();
    }
}

// Business logic class.
// It does not know HOW objects are created.
// It only asks the Factory for a Logistic object.
class LogisticService {

    public void send(String mode) {

        // Factory decides which object to create.
        Logistic logistic = LogisticFactory.getLogistics(mode);

        // Service simply uses the object.
        // It doesn't care whether it's Road or Air.
        logistic.send();
    }
}

public class FactoryPattern {

    public static void main(String[] args) {

        // Client creates the service.
        LogisticService service = new LogisticService();

        // Send using Road transport.
        service.send("road");

        // Send using Air transport.
        service.send("air");
    }
}