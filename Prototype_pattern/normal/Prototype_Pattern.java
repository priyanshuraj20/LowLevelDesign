package Prototype_pattern.normal;

import java.util.*;

//Every Email Template should support
//setting content and sending email.
interface EmailTemplate {

    void setContent(String content);

    void send(String to);
}

class WelcomeEmail implements EmailTemplate {

    private String subject;
    private String content;

    public WelcomeEmail() {

        //Suppose constructor is expensive.

        //Loading HTML Template...
        //Loading CSS...
        //Loading Images...
        //Loading Attachments...
        //Loading Company Logo...
        //Loading Signature...

        subject = "Welcome To TUF+";
        content = "Hi There! Thanks for joining us.";
    }

    @Override
    public void setContent(String content) {

        this.content = content;
    }

    @Override
    public void send(String to) {

        System.out.println("----------------------------");
        System.out.println("To : " + to);
        System.out.println("Subject : " + subject);
        System.out.println("Content : " + content);
    }
}



//======================================================================
// INITIAL IMPLEMENTATION
//======================================================================

//Suppose thousands of users
//register on our platform everyday.
//
//For every new user,
//we are creating a new WelcomeEmail object.
//
//Every constructor execution is expensive.
//
//Because every time it is:
//
//1. Loading HTML Template
//2. Loading CSS
//3. Loading Images
//4. Loading Attachments
//5. Loading Company Logo
//6. Preparing Email Structure
//
//Again and Again.
//
//This is unnecessary because
//all Welcome Emails are almost identical.
//
//Only the content changes.
//
//Current Approach:
//
//new WelcomeEmail();
//new WelcomeEmail();
//new WelcomeEmail();
//
//Problem:
//Object creation is expensive.
//
//Can we use Singleton?
//
//NO.
//
//Singleton creates only ONE global object.
//
//Suppose:
//
//User-1 changes email content.
//
//Then User-2 will also receive
//the modified content.
//
//So Singleton is not suitable.
//
//We need:
//
//Create one predefined object.
//
//Whenever required,
//simply create its copy.
//
//This is exactly where
//Prototype Pattern comes into picture.

public class NormalImplementation {

    public static void main(String[] args) {

        //User-1

        WelcomeEmail email1 = new WelcomeEmail();

        email1.setContent("Welcome Priyanshu!");
        email1.send("priyanshu@gmail.com");


        System.out.println();


        //User-2

        WelcomeEmail email2 = new WelcomeEmail();

        email2.setContent("Welcome Rahul!");
        email2.send("rahul@gmail.com");


        System.out.println();


        //Again creating one more object.

        WelcomeEmail email3 = new WelcomeEmail();

        email3.setContent("Welcome Aman!");
        email3.send("aman@gmail.com");
    }
}