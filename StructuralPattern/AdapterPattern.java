// ============================================================
// ADAPTER DESIGN PATTERN
// Example:
// Initially our project uses PayU.
// Later company wants to integrate Razorpay.
// But Razorpay API is different.
// Adapter Pattern solves this compatibility issue.
// ============================================================


// ------------------------------------------------------------
// STEP 1 : Common Interface
// CheckoutService will always talk to this interface.
// It doesn't care whether payment happens using PayU,
// Razorpay or any other payment gateway.
// ------------------------------------------------------------
interface PaymentGateway {

    // Common method every payment gateway should provide
    void pay(String orderId, double amount);
}



// ------------------------------------------------------------
// STEP 2 : Existing PayU Implementation
// PayU already follows our interface.
// So no problem here.
// ------------------------------------------------------------
class PayUGateway implements PaymentGateway {

    @Override
    public void pay(String orderId, double amount) {

        System.out.println(
                "Paid ₹" + amount +
                        " using PayU for Order : " + orderId);
    }
}



// ------------------------------------------------------------
// STEP 3 : Third Party Razorpay Library
//
// Imagine this class comes from Razorpay SDK (.jar file).
//
// IMPORTANT:
// We DID NOT write this class.
// Therefore we CANNOT modify it.
//
// Also notice:
// It DOES NOT implement PaymentGateway.
//
// And method name is
// makePayment()
// instead of
// pay()
//
// So CheckoutService cannot directly use this class.
// ------------------------------------------------------------
class RazorpayAPI {

    public void makePayment(String invoiceId, double amountInRupees) {

        System.out.println(
                "Paid ₹" + amountInRupees +
                        " using Razorpay for Invoice : " + invoiceId);
    }
}



// ------------------------------------------------------------
// STEP 4 : Adapter Class
//
// This is the MOST IMPORTANT part.
//
// Adapter implements OUR interface.
//
// Inside it, it uses Razorpay API.
//
// It acts like a translator.
//
// CheckoutService says:
//
//      pay(orderId, amount)
//
// Adapter converts it into
//
//      makePayment(invoiceId, amount)
//
// ------------------------------------------------------------
/
class RazorpayAdapter implements PaymentGateway {

    // Third-party API object
    private RazorpayAPI razorpayAPI;

    public RazorpayAdapter() {
        razorpayAPI = new RazorpayAPI();
    }

    //Now these razorpay has the pay method
    @Override
    public void pay(String orderId, double amount) {

        // Translate our method
        // into Razorpay's method
//internally calling the same razorpayAPI  u
        razorpayAPI.makePayment(orderId, amount);
    }
}



// ------------------------------------------------------------
// STEP 5 : Business Logic
//
// CheckoutService ONLY knows PaymentGateway.
//
// It never knows whether payment is done by
// PayU or Razorpay.
//
// This follows
// "Program to Interface, not Implementation"
// ------------------------------------------------------------
class CheckoutService {

    private PaymentGateway paymentGateway;

    // Constructor Injection
    public CheckoutService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void checkout(String orderId, double amount) {

        System.out.println("Processing Order...\n");

        paymentGateway.pay(orderId, amount);

        System.out.println("\nOrder Completed.");
    }
}



// ------------------------------------------------------------
// STEP 6 : Driver Class
// ------------------------------------------------------------
public class AdapterPattern {

    public static void main(String[] args) {

        // =====================================================
        // CASE 1
        // Using PayU
        // =====================================================

        CheckoutService payuCheckout =
                new CheckoutService(new PayUGateway());

        payuCheckout.checkout("ORDER101", 500);

        System.out.println("\n============================\n");


        // =====================================================
        // CASE 2
        // Company switches to Razorpay.
        //
        // CheckoutService code DOES NOT change.
        //
        // Only object changes.
        //
        // This is Adapter Pattern.
        // =====================================================

        CheckoutService razorpayCheckout =
                new CheckoutService(new RazorpayAdapter());

        razorpayCheckout.checkout("ORDER102", 750);
    }
}