package jms.web.jms;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsProviderActiveMQ implements JmsProvider {

	private ActiveMQConnectionFactory qconFactory;
	/* This will keep ActiveMQ alive - this shpuld never be used in production */
	private QueueConnection qcon;
	
	public JmsProviderActiveMQ(ActiveMQConnectionFactory activeMQConnectionFactory) {
		this.qconFactory = activeMQConnectionFactory;	
		try {
			this.qcon = qconFactory.createQueueConnection();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public JmsReceive getJmsListener(String jmsFactory, String queueName, MessageListener messageListener) throws JMSException {
		QueueConnection qcon = qconFactory.createQueueConnection();
		QueueSession qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = qsession.createQueue(queueName);
		QueueReceiver qreceiver = qsession.createReceiver(queue);
		qreceiver.setMessageListener(messageListener);
		qcon.start();
		
		return new JmsReceive(qcon, qsession, qreceiver);
	}

	@Override
	public JmsSend getJmsSend(String jmsFactory, String queueName) throws JMSException, NamingException {
		QueueConnection qcon = qconFactory.createQueueConnection();
		QueueSession qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = qsession.createQueue(queueName);	
		QueueSender qsender = qsession.createSender(queue);
		TextMessage msg = qsession.createTextMessage();
		qcon.start();
		
		return new JmsSend(qcon, qsession, qsender, msg);
	}

	@Override
	public JmsSendAndReceive getJmsSendAndReceive(String jmsFactory, String queueName)
			throws JMSException, NamingException {
		QueueConnection qcon = qconFactory.createQueueConnection();
		QueueSession qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = qsession.createQueue(queueName);
		QueueReceiver qreceiver = qsession.createReceiver(queue);
		QueueSender qsender = qsession.createSender(queue);
		TextMessage msg = qsession.createTextMessage();
		
		qcon.start();		
		
		
		return new JmsSendAndReceive(qcon, qsession, qreceiver, qsender, msg);
	}

	@Override
	public JmsReceive getJmsListener(String jmsFactory, String queueName, String correlationID) throws NamingException, JMSException {
		QueueConnection qcon = qconFactory.createQueueConnection();
		QueueSession qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = qsession.createQueue(queueName);
		QueueReceiver qreceiver = qsession.createReceiver(queue, String.format("JMSCorrelationID='%s'", correlationID));
		qcon.start();
		
		return new JmsReceive(qcon, qsession, qreceiver);
	}

	
}
