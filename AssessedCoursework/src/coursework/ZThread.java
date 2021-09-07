package coursework;

import java.io.IOException;

//This thread is used to repeat the output of the letter Z
public class ZThread extends Thread{

    private BinarySemaphore yBinarySemaphore;
    private BinarySemaphore zBinarySemaphore;
    private Semaphore wSemaphore;
    private int zNum;

    public ZThread(BinarySemaphore yBinarySemaphore, BinarySemaphore zBinarySemaphore, Semaphore wSemaphore) {
        this.yBinarySemaphore = yBinarySemaphore;
        this.zBinarySemaphore = zBinarySemaphore;
        this.wSemaphore = wSemaphore;
    }

    @Override
    public void run() {
        while (true){
            try {

                //Waiting for W threads to wake up
                wSemaphore.P();

                //Waiting for Y threads to wake up
                zBinarySemaphore.P();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("Z");

            //System.out.print("Z");

            //To make the program output more varied sequences of letters each time you run it,
            // make the threads to sleep a random number of milliseconds before printing each letter.

            try {
                Thread.sleep((long)(Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {

                //Print the letter Z
                System.out.print(Test01.outputFile("Z"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*zNum++;
            System.out.println("zNum = " + zNum);*/

            //Wake up Y threads
            yBinarySemaphore.V();


        }
    }
}
