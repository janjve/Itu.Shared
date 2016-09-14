// For week 1
// jaen@itu.dk


public class TestPrinterExperiments {
  public static void main(String[] args) {
    Printer p = new Printer();
    Thread t1 = new Thread(() -> {
      while (true) 
        Printer.print();
    });
    Thread t2 = new Thread(() -> {
      while (true) 
        Printer.print();
    });
    
        t1.start(); t2.start();
    try { t1.join(); t2.join(); }
    catch (InterruptedException exn) { 
      System.out.println("Some thread was interrupted");
    }
    System.out.println("Done with main thread");
  }
}

class Printer {
    public static void print(){
        synchronized (Printer.class){
            System.out.print("-");
            try{
                Thread.sleep(50);
            } catch (InterruptedException exn) { }
            System.out.print("|");
        }
    }
}