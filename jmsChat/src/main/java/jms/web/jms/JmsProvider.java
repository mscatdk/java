package jms.web.jms;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.naming.NamingException;

public interface JmsProvider {

	JmsReceive getJmsListener(String jmsFactory, String queueName, MessageListener messageListener) throws NamingException, JMSException;
	
	JmsReceive getJmsListener(String jmsFactory, String queueName, String correlationID) throws NamingException, JMSException;
	
	JmsSend getJmsSend(String jmsFactory, String queueName) throws JMSException, NamingException;
	
	JmsSendAndReceive getJmsSendAndReceive(String jmsFactory, String queueName) throws JMSException, NamingException;
}
