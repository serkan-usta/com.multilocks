// have issue about synchornized
// 170.using multiple lock

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListWorker {

    Random random = new Random();

    ArrayList<Integer> list1 = new ArrayList<Integer>();
    ArrayList<Integer> list2 = new ArrayList<Integer>();

    // make two different keys to get time half
    private Object lock1 = new Object(); // first key
    private Object lock2 = new Object(); // second key


    public void list1ValueAdd() {

        synchronized (lock1) {

            try {
                Thread.sleep(1);


            } catch (InterruptedException ex) {
                Logger.getLogger(ListWorker.class.getName()).log(Level.SEVERE, null, ex);
            }
            list1.add(random.nextInt(100));
        }


    }

    public void list2ValueAdd() {

        synchronized (lock2) {
            try {
                Thread.sleep(1);


            } catch (InterruptedException ex) {
                Logger.getLogger(ListWorker.class.getName()).log(Level.SEVERE, null, ex);
            }
            list2.add(random.nextInt(100));
        }

    }

    public void assignValue() {

        for (int i = 0; i < 1000; i++) {

            list1ValueAdd();
            list2ValueAdd();

        }
    }
        // the method decreases the current time as half
        public void toRun() {
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    assignValue();

                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    assignValue();

                }
            });
            long start = System.currentTimeMillis();

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ListWorker.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("List1 Size :" + list1.size() + " List2 Size: " + list2.size());

            long finish = System.currentTimeMillis();

            System.out.println("Completion Time : " + (finish - start));


        }
    }

