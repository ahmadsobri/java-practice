public class MultiThread {
    public static void main(String[] args) {
        //ThreadSync.process();

        //thread with wait and notify
        ThreadWaitAndNotify threadWaitAndNotify = ThreadWaitAndNotify.getInstance();
        threadWaitAndNotify.process();

    }
}
