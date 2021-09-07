package coursework;


import java.io.IOException;

//This thread is used to repeat the output of the letter Y
public class YThread extends Thread{

    private BinarySemaphore yBinarySemaphore;
    private BinarySemaphore zBinarySemaphore;
    private Semaphore wSemaphore;
    private int yNum;

    public YThread(BinarySemaphore yBinarySemaphore, BinarySemaphore zBinarySemaphore, Semaphore wSemaphore) {
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

                //Waiting for Z threads to wake up
                yBinarySemaphore.P();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("Y");

            //System.out.print("Y");

            //To make the program output more varied sequences of letters each time you run it,
            // make the threads to sleep a random number of milliseconds before printing each letter.

            try {
                Thread.sleep((long)(Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {

                //Print the letter Y
                System.out.print(Test01.outputFile("Y"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*yNum++;
            System.out.println("yNum = " + yNum);
*/

            //Wake up Z threads
            zBinarySemaphore.V();

        }
    }
}
