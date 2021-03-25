import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String HOST_NAME = "localhost";
    private final static String QUEUE_NAME = "demo-lab-queue";

    public static void sendMessage() {
        try {
            final ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(HOST_NAME);
            final Connection connection = connectionFactory.newConnection();
            final Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            for (int i = 1; i <= 5; i++) {
                final String message = "Hello world= " + i;
                System.out.println("Sending the following message to the queue: " + message);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            }
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
