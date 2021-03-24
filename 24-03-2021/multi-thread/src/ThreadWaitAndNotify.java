import java.util.ArrayList;
import java.util.List;

public class ThreadWaitAndNotify {

    private static ThreadWaitAndNotify instance;

    private List<Integer> list = new ArrayList<>();
    private int LIMIT = 5;
    private int BOTTOM = 0;
    private int value = 0;
    private Object lock = new Object();

    public ThreadWaitAndNotify(){}

    //singleton
    public static ThreadWaitAndNotify getInstance(){
        if(instance == null){
            instance = new ThreadWaitAndNotify();
        }
        return instance;
    }


    private void producre() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LIMIT) {
                    System.out.println("waiting remove list..");
                    lock.wait();
                } else {
                    System.out.println("Adding :" + value);
                    list.add(value);
                    value++;
                    lock.notify(); // memberi sinyal pada threead yang sedang sleep/wait
                }
            }
        }
    }

    private void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == BOTTOM) {
                    System.out.println("waiting add list..");
                    lock.wait();
                } else {
                    System.out.println("Removing :" + list.remove(--value));
                    lock.notify(); // memberi sinyal pada threead yang sedang sleep/wait
                }
            }
        }
    }

    public void process() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producre();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
