package BehaviouralPattern.MementoPattern;

//Naive Approach (Without Memento)
class Document {

    String text;

    void setText(String text) {
        this.text = text;
    }

    void undo() {

        // ??

    }

}

//Question
//
//Undo kaise kare?
//
//Old text hi nahi rakha.
//
//Fir log kya karte hain?
//
//String prev1;
//String prev2;
//String prev3;
//String prev4;
//
//😂
//
//Ya
//
//ArrayList<String>
//
//And Document khud history maintain karta hai.
//
//        Problem?
//
//Document ka kaam sirf text rakhna tha.
//
//History bhi wahi maintain kar raha hai.
//
//Single Responsibility violate.