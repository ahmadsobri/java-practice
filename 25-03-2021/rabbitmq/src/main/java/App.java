
public class App {
    public static void main(String[] args) {
        //send message to the queue
        Producer.sendMessage();

        //get message from queue
        Consumer.receiveMessage();
    }
}
