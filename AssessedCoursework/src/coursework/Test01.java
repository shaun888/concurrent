package coursework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


//The main method of this class is used to run the project
public class Test01 {


    //This method is used to automatically output to a sample output file.
    public static String outputFile(String s) throws IOException {

        //new file
        //sample outputs
        FileWriter fileWriter1 = new FileWriter(new File("Automatically_generated_sample_outputs.txt"), true);
        PrintWriter printWriter1 = new PrintWriter(fileWriter1);
        printWriter1.print(s);
        printWriter1.flush();
        printWriter1.close();

        //return for sout
        return s;
    }
    public static void main(String[] args) {

        //Its initial value is set to 1 and the thread starts immediately with the output W
        BinarySemaphore wBinarySemaphore = new BinarySemaphore(1);//for w

        //Its initial value is set to 0. The thread starts by waiting for the w thread to wake up.
        // That is, it waits for the output of w and then outputs x
        BinarySemaphore xBinarySemaphore = new BinarySemaphore(0);//for x

        //Used to notify wake up waiting threads y and z
        Semaphore wSemaphore = new Semaphore();//for w

        WThread wThread = new WThread(wBinarySemaphore, xBinarySemaphore, wSemaphore);
        XThread xThread = new XThread(wBinarySemaphore, xBinarySemaphore);

        //Its initial value is set to 1 and the thread starts immediately with the output Y
        BinarySemaphore yBinarySemaphore = new BinarySemaphore(1);

        //Its initial value is set to 0. The thread starts by waiting for the y thread to wake up.
        // That is, it waits for the output of y and then outputs z
        BinarySemaphore zBinarySemaphore = new BinarySemaphore(0);
        YThread yThread = new YThread(yBinarySemaphore, zBinarySemaphore, wSemaphore);
        ZThread zThread = new ZThread(yBinarySemaphore, zBinarySemaphore, wSemaphore);

        wThread.start();
        xThread.start();

        yThread.start();
        zThread.start();

        try {
            //Used to put the main thread to sleep for a period of time
            Thread.sleep((long)(Math.random() * 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //To stop the threads the main program
        System.exit(0);
    }
}
