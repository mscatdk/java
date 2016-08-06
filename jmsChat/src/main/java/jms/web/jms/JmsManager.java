package jms.web.jms;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.naming.NamingException;

public class JmsManager {
	
	private final JmsProvider jmsProvider;
	
	public JmsManager(JmsProvider jmsProvider) { 
			this.jmsProvider = jmsProvider;
	}
	
	public JmsReceive getJmsListener(String jmsFactory, String queueName, MessageListener messageListener) throws NamingException, JMSException {		
		return jmsProvider.getJmsListener(jmsFactory, queueName, messageListener);
	}
	
	public JmsReceive getJmsListener(String jmsFactory, String queueName, String correlationID) throws NamingException, JMSException {		
		return jmsProvider.getJmsListener(jmsFactory, queueName, correlationID);
	}

	public JmsSend getJmsSend(String jmsFactory, String queueName) throws JMSException, NamingException {	
		return jmsProvider.getJmsSend(jmsFactory, queueName);
	}
	
	public JmsSendAndReceive getJmsSendAndReceive(String jmsFactory, String queueName)
			throws JMSException, NamingException {
		return jmsProvider.getJmsSendAndReceive(jmsFactory, queueName);
	}
}
