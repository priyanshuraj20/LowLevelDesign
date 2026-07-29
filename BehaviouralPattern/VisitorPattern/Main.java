package BehaviouralPattern.VisitorPattern;

import java.util.*;

// =======================================================
// Visitor Pattern (Behavioral Design Pattern)
//
// Idea:
//
// Product classes remain unchanged.
//
// Every new operation is written inside
// a Visitor class.
//
// Instead of asking:
//
// "Which product is this?"
//
// We simply say:
//
// "Hey Product, accept this visitor."
//
// Product itself sends control to the correct
// visitor method.
// =======================================================



// Every product should be able to accept a visitor.
interface Product {

    // Accept any visitor
    void accept(ProductVisitor visitor);
}



// ---------------- Physical Product ----------------

class PhysicalProduct implements Product {

    @Override
    public void accept(ProductVisitor visitor) {

        // Pass control to visitor.

        // "this" means current PhysicalProduct object.

        visitor.visit(this);
    }
}



// ---------------- Digital Product ----------------

class DigitalProduct implements Product {

    @Override
    public void accept(ProductVisitor visitor) {

        visitor.visit(this);
    }
}



// ---------------- Gift Card ----------------

class GiftCard implements Product {

    @Override
    public void accept(ProductVisitor visitor) {

        visitor.visit(this);
    }
}



// ===================================================
// Visitor Interface
//
// Every visitor must know how to handle
// every product type.
// ===================================================

interface ProductVisitor {

    void visit(PhysicalProduct product);

    void visit(DigitalProduct product);

    void visit(GiftCard product);
}



// ===================================================
// Visitor #1
//
// Prints invoices.
// ===================================================

class InvoiceVisitor implements ProductVisitor {

    @Override
    public void visit(PhysicalProduct product) {

        System.out.println("Invoice for Physical Product");
    }

    @Override
    public void visit(DigitalProduct product) {

        System.out.println("Invoice for Digital Product");
    }

    @Override
    public void visit(GiftCard product) {

        System.out.println("Invoice for Gift Card");
    }
}



// ===================================================
// Visitor #2
//
// Shipping operation.
//
// Notice:
//
// Product classes never changed.
//
// We simply created another Visitor.
// ===================================================

class ShippingVisitor implements ProductVisitor {

    @Override
    public void visit(PhysicalProduct product) {

        System.out.println("Shipping Cost = 100");
    }

    @Override
    public void visit(DigitalProduct product) {

        System.out.println("No Shipping Required");
    }

    @Override
    public void visit(GiftCard product) {

        System.out.println("Gift Cards don't require shipping");
    }
}



public class Main {

    public static void main(String[] args) {

        List<Product> cart = new ArrayList<>();

        cart.add(new PhysicalProduct());
        cart.add(new DigitalProduct());
        cart.add(new GiftCard());


        // Create different operations.

        ProductVisitor invoice = new InvoiceVisitor();

        ProductVisitor shipping = new ShippingVisitor();



        // -----------------------------
        // Print invoices
        // -----------------------------

        for (Product product : cart) {

            // Product decides which visit()
            // method should execute.

            product.accept(invoice);
        }



        System.out.println();



        // -----------------------------
        // Calculate Shipping
        // -----------------------------

        for (Product product : cart) {

            product.accept(shipping);
        }

    }
}


/*

FLOW

Main

↓

product.accept(invoiceVisitor)

↓

PhysicalProduct.accept()

↓

visitor.visit(this)

↓

InvoiceVisitor.visit(PhysicalProduct)

↓

Print Invoice


Notice:

Main never checks

instanceof

No if-else

No casting

Everything happens using polymorphism.


Tomorrow:

Need QR Code?

Simply create

QRCodeVisitor


Need Tax?

Create

TaxVisitor


Need Analytics?

Create

AnalyticsVisitor


Product classes remain unchanged.
*/