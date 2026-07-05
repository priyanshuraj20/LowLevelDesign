interface Machine {
    void print();
    void scan();
    void fax();
}

class Basicprinter implements Machine {

    public void print() {
        System.out.println("Printing");
    }

    public void scan() {
        throw new UnsupportedOperationException();
    }

    public void fax() {
        throw new UnsupportedOperationException();
    }
}
//Basic Printer ko scan() and fax() bhi implement karna pad raha 
//But it is unable :BAD DESIGN

//Correct approach: Follow ISP:

interface Printer{
    void print();
}
interface Scanner{
    void scan();
}
interface Fax{
    void fax();
}
class BasicPrinter implements Printer {

    public void print() {
        System.out.println("Printing");
    }
}

class AdvancedPrinter implements Printer, Scanner, Fax {

    public void print() {
    }

    public void scan() {
    }

    public void fax() {
    }
}
// SO :Prefer multiple small, specific interfaces over one large general-purpose interface."
