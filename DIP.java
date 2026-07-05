 class Bulbb{
    void bulb(){
        System.out.println("Bulb");
    }
}


class Switchh{
    Bulb bulb = new Bulb();

    public void operate(){
        // bulb.turnOn();
    }
}
// Switch tightly coupled hai Bulb se


// Agar kal Fan use karna ho → code change karna padegaAgar kal Fan use karna ho → code change karna padega
class Fann {

    void fan() {
        System.out.println("New Bulb");
    }
}

// So correct approach: Follow DIP

//'create abstraction:'
// Abstraction
interface Switchable {

    void turnOn();
}

// Low-level modules
class Bulb implements Switchable {

    public void turnOn() {
        System.out.println("Bulb ON");
    }
}

class Fan implements Switchable {

    public void turnOn() {
        System.out.println("Fan ON");
    }
}

// High-level module
class Switch {

    private Switchable device;

    // Dependency Injection: Technique| "Dependency kaise deni hai" batata hai|Constructor/setter injection use karo
    public Switch(Switchable device) {
        this.device = device;
    }

    public void operate() {
        device.turnOn();
    }
}

// Main class
public class DIP {

    public static void main(String[] args) {

        Switch bulbSwitch = new Switch(new Bulb());
        bulbSwitch.operate();

        Switch fanSwitch = new Switch(new Fan());
        fanSwitch.operate();
    }
}
//“DIP removes tight coupling by introducing abstraction between high-level and low-level modules.”
