package moti.poc;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ReceiveMessagesAWSApplication {

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

    public MessageConsumer consumeFromQueue(Session session,
                                            String destination,
                                            MessageListener messageListener)
            throws JMSException {
        Queue queue = session.createQueue(destination);
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(messageListener);
        return consumer;
    }

    public static void main(String... args) throws Exception {
        ReceiveMessagesAWSApplication app = new ReceiveMessagesAWSApplication();
        QueueConnectionFactory cf = app.createQueueConnectionFactory();
        QueueConnection conn = app.createQueueConnection(cf);
        QueueSession session = app.createQueueSession(conn);
        // app.sendTextMessageToQueue("Test Message", session);
        // session.close();
        // conn.close();

        MessageConsumer consumer = app.consumeFromQueue( session,
                "TEST_DESTINATION",
                (message -> {
                    //Do Something (this is lambda expression
                    if (message instanceof TextMessage) {
                        TextMessage txtMsg = (TextMessage)message;
                        try {
                            System.out.println(txtMsg.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }

                }));
        conn.start();

        //Free Resources
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    super.run();
                    conn.stop();
                    consumer.close();
                    session.close();
                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });




    }

}
