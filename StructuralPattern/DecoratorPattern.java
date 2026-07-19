package StructuralPattern;
// =====================================================
// Component
// Common interface
// =====================================================
interface Coffee {

    String getDescription();

    int getCost();
}


// =====================================================
// Concrete Component
// Original object
// =====================================================
class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public int getCost() {
        return 100;
    }
}


// =====================================================
// Base Decorator
//
// Notice:
// It also implements Coffee.
//
// It HAS-A Coffee object.
//
// This is the secret of Decorator Pattern.

//abstract class of decorator we can create an object out of it
// =====================================================
abstract class CoffeeDecorator implements Coffee {

    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}


// =====================================================
// Milk Decorator
// Adds Milk
// =====================================================
class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 20;
    }
}


// =====================================================
// Sugar Decorator
// Adds Sugar
// =====================================================
class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 10;
    }
}


// =====================================================
// Whipped Cream Decorator
// =====================================================
class CreamDecorator extends CoffeeDecorator {

    public CreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Cream";
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 40;
    }
}


// =====================================================
// Driver
// =====================================================
public class DecoratorPattern {

    public static void main(String[] args) {

        Coffee coffee = new SimpleCoffee();

        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());

        System.out.println();

        coffee = new MilkDecorator(coffee);

        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());

        System.out.println();

        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());

        System.out.println();

        coffee = new CreamDecorator(coffee);

        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());
    }
}
//Rule yaad rakhna
//Interface (Coffee) → "Main kya kar sakta hoon?" (contract)
//Abstract Decorator (CoffeeDecorator) → "Jo common implementation sab decorators me same hai."
//Concrete Decorators (MilkDecorator, SugarDecorator) → "Extra behaviour add karte hain."