import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumer {
    private final static String HOST_NAME = "localhost";
    private final static String QUEUE_NAME = "demo-lab-queue";

    public static void receiveMessage() {
        try {
            final ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(HOST_NAME);

            final Connection connection = connectionFactory.newConnection();
            final Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            System.out.println("Waiting for messages from the queue. To exit press CTRL+C");

            final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                final String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("Received from message from the queue: " + message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
