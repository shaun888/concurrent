package coursework;

/*
Exercise 1: A BinarySemaphore class [10 marks]
        Derive a BinarySemaphore class from the Semaphore class defined as above.
        Notice that value was declared protected in the Semaphore class, so you can use it directly in any class that extends this class.
        Remember that while writing the definition of the constructor of a subclass, if you want to call a constructor of the superclass you must make this call the first statement.
*/

public class BinarySemaphore extends Semaphore{
    public BinarySemaphore() {
    }

    public BinarySemaphore(int initial) {
        super(initial);
    }

   //The p and v methods are rewritten here because the binary signal can only be 0 or 1.

    @Override
    public synchronized void P() throws InterruptedException {
        while (value==0) {
            wait();
        }
        value = 0;
    }

    @Override
    public synchronized void V() {
        value = 1;
        notify();
    }
}
