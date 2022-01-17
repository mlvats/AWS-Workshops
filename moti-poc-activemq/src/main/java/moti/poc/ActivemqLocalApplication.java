package moti.poc;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActivemqLocalApplication {


    //1. Create Connection Factory, we can create generic or Queue.
  /*  public ConnectionFactory createConnectionFactory() {
        return new ActiveMQConnectionFactory(
                "tcp://localhost:61616"
        );
    }*/

    public QueueConnectionFactory createQueueConnectionFactory() {
        return new ActiveMQConnectionFactory(
                "tcp://localhost:61616"
        );
    }

    //2. Once we have Connection Factory, we need Connection, multiple ways we can do.
    //Generic Connection or Queue Connection or Topic Connection

    public QueueConnection createQueueConnection(QueueConnectionFactory cf)
            throws JMSException {
        return cf.createQueueConnection();
    }

    // 3. Create a Session, Its a transactional Scope for one or more JMS Operations
    //We can use transactions, or acknowledgement mode. AUTO_ACKNOWLEDGE is option, check other options also.

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
        ActivemqLocalApplication app = new ActivemqLocalApplication();
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
