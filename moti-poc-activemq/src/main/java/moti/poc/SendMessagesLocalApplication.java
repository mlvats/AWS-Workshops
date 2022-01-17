package moti.poc;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SendMessagesLocalApplication {

    //1. Create Connection Factory, we can create generic or Queue.
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

    public static void main(String... args) throws Exception {
        ActivemqLocalApplication app = new ActivemqLocalApplication();
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
