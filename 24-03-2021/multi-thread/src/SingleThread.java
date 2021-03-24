public class SingleThread implements Runnable{
    @Override
    public void run() {

    }

    public static void main(String[] args) {
        Thread thread = new Thread("Thread One");
        thread.start();
        System.out.println(thread.getName());
    }
}
