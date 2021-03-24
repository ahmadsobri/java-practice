public class ThreadSync {
    //Note:
    //If one thread is writing some data and another thread which is reading data at the same time, might create inconsistency in the application.
    //Java has provided synchronized methods to implement synchronized behavior
    //you can try remove synchronized behavior

    private static int counter = 0;
    private static synchronized void increment() {
        ++counter;
    }

    public static void process() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            //to join the thread and hold until finished before print out bellow
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print(counter);
    }
}
