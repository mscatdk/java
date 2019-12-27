package jms.web.jms;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JmsProviderWebLogic implements JmsProvider {

	private final String url;
	private final String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	
	public JmsProviderWebLogic(String url) {
		this.url = url;
	}
	
	public InitialContext getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
		env.put(Context.PROVIDER_URL, url);
		return new InitialContext(env);
	}

	@Override
	public JmsReceive getJmsListener(String jmsFactory, String queueName, MessageListener messageListener) throws JMSException, NamingException {
		InitialContext ctx = getInitialContext();
		
		QueueConnectionFactory qconFactory = (QueueConnectionFactory) ctx.lookup(jmsFactory);
		QueueConnection qcon = qconFactory.createQueueConnection();
		QueueSession qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = (Queue) ctx.lookup(queueName);
		QueueReceiver qreceiver = qsession.createReceiver(queue);
		qreceiver.setMessageListener(messageListener);
		qcon.start();
		
		return new JmsReceive(qcon, qsession, qreceiver);
	}

	@Override
	public JmsSend getJmsSend(String jmsFactory, String queueName) throws JMSException, NamingException {
		InitialContext ctx = getInitialContext();
		
		QueueConnectionFactory qconFactory = (QueueConnectionFactory) ctx.lookup(jmsFactory);
		QueueConnection qcon = qconFactory.createQueueConnection();
		QueueSession qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = (Queue) ctx.lookup(queueName);	
		QueueSender qsender = qsession.createSender(queue);
		TextMessage msg = qsession.createTextMessage();
		qcon.start();
		
		return new JmsSend(qcon, qsession, qsender, msg);
	}

	@Override
	public JmsSendAndReceive getJmsSendAndReceive(String jmsFactory, String queueName)
			throws JMSException, NamingException {
		InitialContext ctx = getInitialContext();
		
		QueueConnectionFactory qconFactory = (QueueConnectionFactory) ctx.lookup(jmsFactory);
		QueueConnection qcon = qconFactory.createQueueConnection();
		QueueSession qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = (Queue) ctx.lookup(queueName);
		QueueReceiver qreceiver = qsession.createReceiver(queue);
		QueueSender qsender = qsession.createSender(queue);
		TextMessage msg = qsession.createTextMessage();
		
		qcon.start();		
		
		
		return new JmsSendAndReceive(qcon, qsession, qreceiver, qsender, msg);
	}

	@Override
	public JmsReceive getJmsListener(String jmsFactory, String queueName, String correlationID) throws NamingException, JMSException {
		InitialContext ctx = getInitialContext();
		
		QueueConnectionFactory qconFactory = (QueueConnectionFactory) ctx.lookup(jmsFactory);
		QueueConnection qcon = qconFactory.createQueueConnection();
		QueueSession qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = (Queue) ctx.lookup(queueName);
		QueueReceiver qreceiver = qsession.createReceiver(queue, String.format("JMSCorrelationID='%s'", correlationID));
		qcon.start();
		
		return new JmsReceive(qcon, qsession, qreceiver);
	}

}
