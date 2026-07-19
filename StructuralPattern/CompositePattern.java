package StructuralPattern;

import java.util.ArrayList;
import java.util.List;

// =====================================================

//Intuition
//
//Amazon cart me kya hota hai?
//
//Cart
//│
//├── iPhone
//├── AirPods
//├── Books Bundle
//│      ├── Java Book
//│      ├── LLD Book
//│      └── DSA Book
//└── Charger
//
//Notice:
//
//iPhone → Single Product (Leaf)
//AirPods → Single Product (Leaf)
//Books Bundle → Group of Products (Composite)
//Cart → Group of Products (Composite)
//
//Client sirf ye bolega
//
//cart.showPrice();
//
//Usse nahi pata andar single product hai ya bundle.
//
//Yahi Composite Pattern hai.
// =====================================================

// =====================================================
// COMPONENT
// Common interface.
//
// Chahe Single Product ho ya Bundle,
// dono ka price nikalna possible hona chahiye.
// =====================================================
interface CartItem {

    void showPrice();
}



// =====================================================
// LEAF
//
// Single Product
//
// Iske andar aur koi item nahi hota.
// =====================================================
class Product implements CartItem {

    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void showPrice() {

        System.out.println(name + " : ₹" + price);
    }
}



// =====================================================
// COMPOSITE
//
// Bundle bhi CartItem hai.
//
// Iske andar aur Products ya Bundles dono aa sakte hain.
// =====================================================
class ProductBundle implements CartItem {

    private String bundleName;

    // Composite ka magic.
    // List me Product bhi aa sakta hai.
    // Bundle bhi aa sakta hai.
    private List<CartItem> items = new ArrayList<>();


    public ProductBundle(String bundleName) {
        this.bundleName = bundleName;
    }


    public void add(CartItem item) {
        items.add(item);
    }


    @Override
    public void showPrice() {

        System.out.println("\n===== " + bundleName + " =====");

        // Har item ko bolo apna price dikhao.
        // Mujhe nahi pata ye Product hai ya Bundle.
        for (CartItem item : items) {
            item.showPrice();
        }
    }
}



// =====================================================
// DRIVER
// =====================================================
public class CompositePattern {

    public static void main(String[] args) {

        // -------------------------------
        // Single Products
        // -------------------------------

        Product iphone = new Product("iPhone 16", 80000);

        Product airpods = new Product("AirPods", 18000);

        Product charger = new Product("Charger", 2000);



        // -------------------------------
        // Books
        // -------------------------------

        Product javaBook = new Product("Java Book", 700);

        Product dsaBook = new Product("DSA Book", 900);

        Product lldBook = new Product("LLD Book", 1000);



        // -------------------------------
        // Bundle of Books
        // -------------------------------

        ProductBundle booksBundle =
                new ProductBundle("Books Bundle");

        booksBundle.add(javaBook);

        booksBundle.add(dsaBook);

        booksBundle.add(lldBook);



        // -------------------------------
        // Amazon Cart
        // -------------------------------

        ProductBundle amazonCart =
                new ProductBundle("Amazon Cart");

        amazonCart.add(iphone);

        amazonCart.add(airpods);

        amazonCart.add(charger);

        amazonCart.add(booksBundle);



        // Client only calls one method.
        amazonCart.showPrice();
    }
}

//output:===== Amazon Cart =====
//
//iPhone 16 : ₹80000
//AirPods : ₹18000
//Charger : ₹2000
//
//===== Books Bundle =====
//
//Java Book : ₹700
//DSA Book : ₹900
//LLD Book : ₹1000


//                   CartItem
//                      ▲
//        ┌─────────────┴─────────────┐
//        │                           │
//    Product                  ProductBundle
//                                    │
//                    List<CartItem> items
//                                    │
//             ┌──────────┬───────────┬───────────┐
//             │          │           │
//          Product    Product    ProductBundle
//                                    │
//                           ┌────────┴────────┐
//                           │                 │
//                        Product          Product