package coursework;


import java.io.IOException;

//This thread is used to repeat the output of the letter W
public class WThread extends Thread{

    private BinarySemaphore wBinarySemaphore;
    private BinarySemaphore xBinarySemaphore;
    private Semaphore wSemaphore;
    private int wNum;

    public WThread(BinarySemaphore wBinarySemaphore, BinarySemaphore xBinarySemaphore, Semaphore wSemaphore) {
        this.wBinarySemaphore = wBinarySemaphore;
        this.xBinarySemaphore = xBinarySemaphore;
        this.wSemaphore = wSemaphore;
    }

    @Override
    public void run() {
        while (true){
            try {
                //Waiting for X threads to wake up
                wBinarySemaphore.P();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("W = " + wBinarySemaphore.value);
            //System.out.println("W");
            //System.out.print("W");

            //To make the program output more varied sequences of letters each time you run it,
            // make the threads to sleep a random number of milliseconds before printing each letter.

            try {
                Thread.sleep((long)(Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                //Print the letter W
                System.out.print(Test01.outputFile("W"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*wNum++;
            System.out.println("wNum = " + wNum);*/

            //Wake up X threads
            xBinarySemaphore.V();

            //Wake up Y and Z threads
            wSemaphore.V();

        }
    }
}
