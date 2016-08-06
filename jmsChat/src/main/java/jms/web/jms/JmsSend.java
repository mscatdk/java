package jms.web.jms;

import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

public class JmsSend implements AutoCloseable {
	private QueueConnection qcon;
	private QueueSession qsession;
	private QueueSender qsender;
	private TextMessage msg;

	public JmsSend(QueueConnection qcon, QueueSession qsession, QueueSender qsender, TextMessage msg) {
		this.qcon = qcon;
		this.qsession = qsession;
		this.qsender = qsender;
		this.msg = msg;
	}

	public void send(String message, String correlationID) throws JMSException {
		msg.setText(message);
		msg.setJMSCorrelationID(correlationID);
		qsender.send(msg);
	}

	public void close() throws JMSException {
		qsender.close();
		qsession.close();
		qcon.close();
	}	
}
