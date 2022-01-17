package moti.poc;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;


public class SendMessagesAWSApplication {

    // Specify the connection parameters.
    private final static String WIRE_LEVEL_ENDPOINT
            = "ssl://b-bf89dd20-0a82-4320-afce-ab77f6d55d46-1.mq.us-east-1.amazonaws.com:61617";
    private final static String ACTIVE_MQ_USERNAME = "moti";
    private final static String ACTIVE_MQ_PASSWORD = "zxcvbnmasdfg";

    //1. Create Connection Factory, we can create generic or Queue.
    public QueueConnectionFactory createQueueConnectionFactory() {

        // Create a connection factory.
        final ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(WIRE_LEVEL_ENDPOINT);

        // Pass the username and password.
        connectionFactory.setUserName(ACTIVE_MQ_USERNAME);
        connectionFactory.setPassword(ACTIVE_MQ_PASSWORD);
        return connectionFactory;
    }

    //2. Once we have Connection Factory, we need Connection, multiple ways we can do.
    public QueueConnection createQueueConnection(QueueConnectionFactory cf)
            throws JMSException {
        return cf.createQueueConnection();
    }

    // 3. Create a Session, Its a transactional Scope for one or more JMS Operations
    public QueueSession createQueueSession(QueueConnection connection)
            throws JMSException {
        return connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    // 4. Send Text messages..  we can send any JMS type messages.
    public void sendTextMessageToQueue(String message,
                                       QueueSession session) throws JMSException {
        Queue queue = session.createQueue("TEST_DESTINATION");
        TextMessage msg = session.createTextMessage(message);
        QueueSender messageProducer = session.createSender(queue);
        messageProducer.send(msg);
    }

    public static void main(String... args) throws Exception {
        SendMessagesAWSApplication app = new SendMessagesAWSApplication();
        QueueConnectionFactory cf = app.createQueueConnectionFactory();
        QueueConnection conn = app.createQueueConnection(cf);
        QueueSession session = app.createQueueSession(conn);

        for (int x=0; x<100; x++) {
            TextMessage textMessage = session.createTextMessage("Message " + x);
            System.out.println(textMessage.getText());
            app.sendTextMessageToQueue(textMessage.getText(), session);

        }

        session.close();
        conn.close();

    }

}
