package coursework;

import java.io.IOException;


//This thread is used to repeat the output of the letter X
public class XThread extends Thread{

    private BinarySemaphore wBinarySemaphore;
    private BinarySemaphore xBinarySemaphore;
    private int xNum;

    public XThread(BinarySemaphore wBinarySemaphore, BinarySemaphore xBinarySemaphore) {
        this.wBinarySemaphore = wBinarySemaphore;
        this.xBinarySemaphore = xBinarySemaphore;
    }

    @Override
    public void run() {
        while (true){
            try {
                //Waiting for W threads to wake up
                xBinarySemaphore.P();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("X");
            //System.out.print("X");

            //To make the program output more varied sequences of letters each time you run it,
            // make the threads to sleep a random number of milliseconds before printing each letter.

            try {
                Thread.sleep((long)(Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                //Print the letter X
                System.out.print(Test01.outputFile("X"));
            } catch (IOException e) {
                e.printStackTrace();
            }


            /*xNum++;
            System.out.println("xNum = " + xNum);*/

            //System.out.println("X = " + xBinarySemaphore.value);

            //Wake up W threads
            wBinarySemaphore.V();


        }
    }
}
