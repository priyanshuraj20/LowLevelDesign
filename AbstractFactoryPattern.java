interface paymentGatway {
    void processPayment(double amt);
}

//------------------------ INDIA PAYMENT GATEWAYS ------------------------

class RazorPay implements paymentGatway {
    public void processPayment(double amount) {
        System.out.println("Processing payment using RazorPay : " + amount);
    }
}

class PayUGatway implements paymentGatway {
    public void processPayment(double amount) {
        System.out.println("Processing payment using PayU : " + amount);
    }
}

interface Invoice {
    void genrateInvoice();
}

class GstInvoice implements Invoice {
    public void genrateInvoice() {
        System.out.println("Generating GST Invoice");
    }
}



//======================================================================
// THESE WAS OUR INITIAL IMPLEMENTATION
//======================================================================

//These was the checkout service but violation SRP of solid principle.
//these should only do payment and invoice generation,
//not decide what type of gateway or invoice should be created.
//
//Problem:
//1. CheckoutService is creating RazorPay/PayU objects.
//2. If a new payment gateway comes, CheckoutService must change.
//3. Object creation + Business Logic are mixed together.


//checkout service:

//class Checkout{
//
//    private String gateWayType;
//
//    public Checkout(String gateWayType){
//        this.gateWayType = gateWayType;
//    }
//
//    public void checkout(){
//
//        if(gateWayType.equalsIgnoreCase("razorpay")){
//
//            paymentGatway paymentGatway = new RazorPay();
//            paymentGatway.processPayment(100);
//
//        }else{
//
//            paymentGatway paymentGatway = new PayUGatway();
//            paymentGatway.processPayment(100);
//        }
//
//        Invoice invoice = new GstInvoice();
//        invoice.genrateInvoice();
//    }
//}



//======================================================================
// FACTORY METHOD
//======================================================================

//Creating IndiaFactory for handling object creation (solving SRP).
//CheckoutService should not be concerned about creating gateway objects.
//
//Now CheckoutService will simply ask the factory:
//"Give me the required Payment Gateway."

class IndiaFactory {

    public static paymentGatway createPaymentGateway(String gateWayType) {

        if (gateWayType.equalsIgnoreCase("razorpay")) {
            return new RazorPay();
        }

        return new PayUGatway();
    }

    public static Invoice createInvoice() {
        return new GstInvoice();
    }
}



//CheckoutService is now responsible only for checkout.
//Object creation responsibility has been shifted to IndiaFactory.

class CheckoutService {

    private String gateWayType;

    public CheckoutService(String gateWayType) {
        this.gateWayType = gateWayType;
    }

    public void checkOut(double amount) {

        paymentGatway paymentGatway =
                IndiaFactory.createPaymentGateway(gateWayType);

        paymentGatway.processPayment(amount);

        Invoice invoice = IndiaFactory.createInvoice();

        invoice.genrateInvoice();
    }
}



//Till now everything is good.
//
//CheckoutService is no longer dependent on RazorPay or PayU.
//
//But our application currently supports only INDIA.


//======================================================================
// NEW REQUIREMENT
//======================================================================

//Now the question arises,
//what if we move our service outside India?
//
//Suppose our company launches in USA.
//
//In USA:
//Payment Methods:
//1. PayPal
//2. Stripe
//
//Invoice generation will also be different.
//
//So CheckoutService should be expandable to handle USA service as well.


//------------------------ US PAYMENT GATEWAYS ------------------------

class PayPalGateway implements paymentGatway {

    public void processPayment(double amount) {
        System.out.println("Processing payment using PayPal : " + amount);
    }
}

class StripeGateway implements paymentGatway {

    public void processPayment(double amount) {
        System.out.println("Processing payment using Stripe : " + amount);
    }
}

class USInvoice implements Invoice {

    public void genrateInvoice() {
        System.out.println("Generating US Tax Invoice");
    }
}



//======================================================================
// WHY FACTORY METHOD IS NOT ENOUGH?
//======================================================================

//Brute force solution can be:
//
//Pass one more parameter called countryCode.
//
//Then:
//
//if(country.equals("India")){
//      IndiaFactory.createPaymentGateway(...);
//}
//else{
//      USFactory.createPaymentGateway(...);
//}
//
//Again CheckoutService will have if-else.
//
//Tomorrow Canada comes.
//Again modify CheckoutService.
//
//Tomorrow Japan comes.
//Again modify CheckoutService.
//
//CheckoutService is again taking one more responsibility:
//
//"Which country's factory should I use?"
//
//This violates:
//
//1. SRP
//2. Open Closed Principle
//
//This is where Abstract Factory comes into picture.


//======================================================================
// ABSTRACT FACTORY
//======================================================================

//Instead of creating only ONE object,
//Abstract Factory creates a FAMILY of related objects.
//
//India Family:
//1. RazorPay / PayU
//2. GST Invoice
//
//USA Family:
//1. PayPal / Stripe
//2. US Invoice

interface CountryFactory {

    paymentGatway createPaymentGateway(String gateWayType);

    Invoice createInvoice();
}



//Concrete Factory for INDIA.

class IndiaFactory2 implements CountryFactory {

    @Override
    public paymentGatway createPaymentGateway(String gateWayType) {

        if (gateWayType.equalsIgnoreCase("razorpay")) {
            return new RazorPay();
        }

        return new PayUGatway();
    }

    @Override
    public Invoice createInvoice() {
        return new GstInvoice();
    }
}



//Concrete Factory for USA.

class USFactory implements CountryFactory {

    @Override
    public paymentGatway createPaymentGateway(String gateWayType) {

        if (gateWayType.equalsIgnoreCase("paypal")) {
            return new PayPalGateway();
        }

        return new StripeGateway();
    }

    @Override
    public Invoice createInvoice() {
        return new USInvoice();
    }
}



//======================================================================
// NEW CHECKOUT SERVICE
//======================================================================

//CheckoutService no longer knows:
//
//1. India or USA.
//2. RazorPay or PayPal.
//3. GSTInvoice or USInvoice.
//
//It simply depends upon CountryFactory.
//
//This also follows Dependency Inversion Principle.

class CheckoutServiceV2 {

    private CountryFactory factory;
    private String gateWayType;

    public CheckoutServiceV2(CountryFactory factory,
                             String gateWayType) {

        this.factory = factory;
        this.gateWayType = gateWayType;
    }

    public void checkOut(double amount) {

        paymentGatway paymentGatway =
                factory.createPaymentGateway(gateWayType);

        paymentGatway.processPayment(amount);

        Invoice invoice = factory.createInvoice();

        invoice.genrateInvoice();
    }
}



//======================================================================
// CLIENT
//======================================================================

//Client/Application layer decides
//which Concrete Factory should be injected.
//
//CheckoutService never changes.
//
//Only injected factory changes.
//
//This is the biggest advantage of Abstract Factory.

public class AbstractFactoryPattern {

    public static void main(String[] args) {

        System.out.println("----------- INDIA -----------");

        CheckoutServiceV2 indiaCheckout =
                new CheckoutServiceV2(
                        new IndiaFactory2(),
                        "razorpay"
                );

        indiaCheckout.checkOut(1000);

        System.out.println();

        System.out.println("----------- USA -----------");

        CheckoutServiceV2 usCheckout =
                new CheckoutServiceV2(
                        new USFactory(),
                        "paypal"
                );

        usCheckout.checkOut(5000);
    }
}