package Prototype_pattern.DeepCopy;

import java.util.*;

//Every Email Template should be able
//to create its own independent copy.
interface EmailTemplate {

    EmailTemplate clone();

    void setContent(String content);

    void send(String to);
}

class WelcomeEmail implements EmailTemplate {

    private String subject;
    private String content;

    //Mutable Object.
    //Unlike Shallow Copy,
    //this List should also be copied.
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

    //Private Constructor
    //Used only while creating cloned object.
    private WelcomeEmail(String subject,
                         String content,
                         List<String> attachments){

        this.subject = subject;
        this.content = content;
        this.attachments = attachments;
    }

    @Override
    public WelcomeEmail clone() {

        //DEEP COPY

        //Instead of copying
        //the reference of List,
        //we create a NEW List.

        List<String> newAttachments =
                new ArrayList<>(this.attachments);

        //Create completely new object.

        return new WelcomeEmail(
                this.subject,
                this.content,
                newAttachments
        );
    }

    @Override
    public void setContent(String content) {

        this.content = content;
    }

    public void addAttachment(String file){

        attachments.add(file);
    }

    @Override
    public void send(String to){

        System.out.println("---------------------------");
        System.out.println("To : " + to);
        System.out.println("Subject : " + subject);
        System.out.println("Content : " + content);
        System.out.println("Attachments : " + attachments);
    }
}



//======================================================================
// WHY DEEP COPY?
//======================================================================

//In Shallow Copy:
//
//Objects were different.
//
//BUT
//
//Their attachment List
//was shared.
//
//So changing one object's List
//also changed another object's List.
//
//To solve this,
//we perform DEEP COPY.
//
//Deep Copy creates:
//
//1. New Object.
//
//2. New Nested Mutable Objects.
//
//Now every cloned object
//becomes completely independent.
//
//Changes made in one object
//will never affect another object.


//======================================================================
// TEMPLATE REGISTRY
//======================================================================

//Registry stores already created objects.
//
//Whenever client asks
//for a template,
//
//Instead of creating
//new object using new,
//
//Simply return
//a cloned copy.
//
//This is the real use
//of Prototype Pattern.

class EmailTemplateRegistry{

    private static final Map<String,EmailTemplate> templates =
            new HashMap<>();

    static{

        //Created only once.

        templates.put("WELCOME",
                new WelcomeEmail());

        //Future

        //templates.put("PURCHASE",
        //        new PurchaseEmail());

        //templates.put("FORGET",
        //        new ForgetPasswordEmail());
    }

    public static EmailTemplate getTemplate(String type){

        EmailTemplate template = templates.get(type);

        if(template == null){

            throw new RuntimeException("Template Not Found");
        }

        //Return Deep Cloned Object.

        return template.clone();
    }
}



//======================================================================
// CLIENT
//======================================================================

//Every user receives
//his own independent copy.
//
//Changing one object
//does not affect another.

public class DeepCopy {

    public static void main(String[] args) {

        //User-1

        WelcomeEmail email1 =
                (WelcomeEmail) EmailTemplateRegistry.getTemplate("WELCOME");

        email1.setContent("Welcome Priyanshu!");

        email1.addAttachment("OfferLetter.pdf");



        //User-2

        WelcomeEmail email2 =
                (WelcomeEmail) EmailTemplateRegistry.getTemplate("WELCOME");

        email2.setContent("Welcome Rahul!");



        //Notice carefully.
        //
        //OfferLetter.pdf
        //will NOT appear
        //inside email2.
        //
        //Reason:
        //
        //Both objects
        //have different Lists.

        email1.send("priyanshu@gmail.com");

        email2.send("rahul@gmail.com");



        System.out.println();

        System.out.println("Same Object ? " + (email1 == email2));

        //false
        //
        //Different Objects.
        //
        //Different Lists.
        //
        //Completely Independent.
    }
}