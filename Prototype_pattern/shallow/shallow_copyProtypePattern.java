package Prototype_pattern.shallow;

import java.util.*;

//Every Email Template should be able
//to create its own copy.
//
//For Prototype Pattern,
//Java provides Cloneable interface.
interface EmailTemplate extends Cloneable {

    EmailTemplate clone();

    void setContent(String content);

    void send(String to);
}

class WelcomeEmail implements EmailTemplate {

    private String subject;
    private String content;

    //Mutable Object.
    //We are adding this only to understand
    //the drawback of Shallow Copy.
    private List<String> attachments;

    public WelcomeEmail() {

        //Suppose constructor is expensive.

        //Loading HTML Template...
        //Loading CSS...
        //Loading Images...
        //Loading Attachments...
        //Loading Company Logo...

        subject = "Welcome To TUF+";
        content = "Hi There! Thanks for joining us.";

        attachments = new ArrayList<>();
        attachments.add("RoadMap.pdf");
    }

    @Override
    public WelcomeEmail clone() {

        //Instead of creating object
        //using new keyword,
        //we are creating
        //a copy of existing object.

        //super.clone() performs
        //SHALLOW COPY.

        //Primitive fields are copied.

        //Object references are copied.

        //Actual nested objects
        //are NOT copied.

        try {

            return (WelcomeEmail) super.clone();

        } catch (CloneNotSupportedException e) {

            throw new RuntimeException("Clone Failed", e);
        }
    }

    @Override
    public void setContent(String content) {

        this.content = content;
    }

    public void addAttachment(String file) {

        attachments.add(file);
    }

    @Override
    public void send(String to) {

        System.out.println("---------------------------");
        System.out.println("To : " + to);
        System.out.println("Subject : " + subject);
        System.out.println("Content : " + content);
        System.out.println("Attachments : " + attachments);
    }
}



//======================================================================
// PROTOTYPE PATTERN
//======================================================================

//Instead of creating object
//again and again using:
//
//new WelcomeEmail();
//
//We create one object.
//
//Whenever required,
//simply clone it.
//
//This avoids expensive constructor execution.
//
//But there is one problem.
//
//super.clone() performs
//SHALLOW COPY.
//
//Primitive variables are copied.
//
//Immutable objects like String
//are fine because they cannot change.
//
//BUT
//
//Mutable Objects like:
//
//List
//Map
//Set
//Custom Objects
//
//are NOT copied.
//
//Only their reference is copied.
//
//That means both objects
//share the same List.
//
//This is the drawback
//of Shallow Copy.

public class shallow_copyProtypePattern{

    public static void main(String[] args) {

        //Create Original Object

        WelcomeEmail original = new WelcomeEmail();

        //Clone-1

        WelcomeEmail email1 = original.clone();

        email1.setContent("Welcome Priyanshu!");
        email1.addAttachment("OfferLetter.pdf");



        //Clone-2

        WelcomeEmail email2 = original.clone();

        email2.setContent("Welcome Rahul!");



        //Notice carefully.
        //
        //OfferLetter.pdf was added
        //only inside email1.
        //
        //But it will also appear
        //inside email2.
        //
        //Reason:
        //
        //attachments List
        //is shared by both objects.

        email1.send("priyanshu@gmail.com");

        email2.send("rahul@gmail.com");



        System.out.println();

        System.out.println("Same Object ? " + (email1 == email2));

        //false
        //
        //Objects are different.
        //
        //But their attachment List
        //is the same.
    }
}