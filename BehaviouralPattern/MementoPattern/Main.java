package BehaviouralPattern.MementoPattern;
//Memento Pattern Code
import java.util.*;

// ==========================================================
// Memento Pattern (Behavioral Design Pattern)
//
// Definition:
// Memento Pattern allows us to save and restore an object's
// previous state without exposing its internal details.
//
// Real Life Example:
// MS Word -> Undo / Redo
// Photoshop -> Undo
// VS Code -> Ctrl + Z
// ==========================================================



// ==========================================================
// MEMENTO
//
// A snapshot of the Document.
//
// It ONLY stores the state.
// It doesn't know anything about history or document logic.
// ==========================================================

class DocumentMemento {

    // Snapshot of document text
    private final String text;

    public DocumentMemento(String text) {
        this.text = text;
    }

    // Return saved state
    public String getSavedText() {
        return text;
    }
}



// ==========================================================
// ORIGINATOR
//
// Main object whose state we want to save.
//
// Responsibilities:
// 1. Change state
// 2. Save current state
// 3. Restore previous state
// ==========================================================

class Documen {

    private String text;

    // Change document content
    public void setText(String text) {
        this.text = text;
    }

    // Current content
    public String getText() {
        return text;
    }

    // Save current state
    public DocumentMemento save() {

        System.out.println("Saving Snapshot -> " + text);

        // Create snapshot
        return new DocumentMemento(text);
    }

    // Restore old state
    public void restore(DocumentMemento memento) {

        if (memento == null) {
            return;
        }

        text = memento.getSavedText();

        System.out.println("Restored Snapshot -> " + text);
    }
}



// ==========================================================
// CARETAKER
//
// Maintains history.
//
// Responsibilities:
// 1. Store snapshots
// 2. Return previous snapshot during Undo
//
// It NEVER modifies Document.
// It NEVER looks inside Memento.
// ==========================================================

class History {

    private Stack<DocumentMemento> history = new Stack<>();


    // Save snapshot
    public void save(DocumentMemento memento) {

        history.push(memento);
    }


    // Undo

    public DocumentMemento undo() {

        if (history.isEmpty()) {

            System.out.println("Nothing to Undo");

            return null;
        }

        // Remove current state
        history.pop();

        // If nothing remains,
        // there is no previous state.
        if (history.isEmpty()) {

            System.out.println("No Previous State");

            return null;
        }

        // Return previous snapshot
        return history.peek();
    }
}



// ==========================================================
// MAIN
// ==========================================================

public class Main {

    public static void main(String[] args) {

        Document document = new Document();

        History history = new History();


        // ------------------------------------
        // Initial Version
        // ------------------------------------

        document.setText("Hello");

        // Save snapshot
        history.save(document.save());


        // ------------------------------------
        // Second Version
        // ------------------------------------

        document.setText("Hello World");

        history.save(document.save());


        // ------------------------------------
        // Third Version
        // ------------------------------------

        document.setText("Hello World Java");

        history.save(document.save());


        System.out.println();
        System.out.println("Current Document : " + document.getText());



        // ====================================
        // Undo
        // ====================================

        System.out.println();
        System.out.println("Performing Undo...");

        document.restore(history.undo());

        System.out.println("Current Document : " + document.getText());



        // ====================================
        // Undo Again
        // ====================================

        System.out.println();
        System.out.println("Performing Undo Again...");

        document.restore(history.undo());

        System.out.println("Current Document : " + document.getText());

    }
}


/*

======================== FLOW ========================

User writes

        ↓

Document.setText()

        ↓

Document.save()

        ↓

DocumentMemento
(Snapshot Created)

        ↓

History.save()

        ↓

User keeps editing...

        ↓

Undo

        ↓

History.undo()

        ↓

Returns Previous Snapshot

        ↓

Document.restore()

        ↓

Old State Restored



======================== OUTPUT ========================

Saving Snapshot -> Hello

Saving Snapshot -> Hello World

Saving Snapshot -> Hello World Java


Current Document : Hello World Java


Performing Undo...

Restored Snapshot -> Hello World

Current Document : Hello World


Performing Undo Again...

Restored Snapshot -> Hello

Current Document : Hello



======================== ROLES ========================

Originator
-----------
Document

Creates Snapshot
Restores Snapshot


Memento
--------
DocumentMemento

Stores Previous State


Caretaker
---------
History

Maintains History
Provides Undo


======================== INTERVIEW ========================

Q. When should we use Memento Pattern?

Answer:

Whenever we need Undo/Redo functionality.

Examples

✔ MS Word
✔ VS Code
✔ Photoshop
✔ Browser History
✔ Game Save Checkpoints


Advantages

✔ Supports Undo

✔ Keeps history outside the main object

✔ Follows Single Responsibility Principle

✔ Doesn't expose internal state


*/
