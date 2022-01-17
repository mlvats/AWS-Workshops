package moti.poc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;


@Service
public class EmailGenerator {

    private static final Log LOG = LogFactory.getLog(EmailGenerator.class);

    private JmsTemplate jmsTemplate;

    @Autowired
    public EmailGenerator(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @SuppressWarnings("Duplicates")
    @Scheduled(fixedDelay = 5000)
    public void generateEmail() {
        Email email = new Email();
        email.setEmailAddress("grant@grantlittle.me");
        email.setName("Grant");
        email.setMessage("This is an example email");
        LOG.info("Sending email " + email);
        jmsTemplate.convertAndSend("Emails", email);
        };

}
