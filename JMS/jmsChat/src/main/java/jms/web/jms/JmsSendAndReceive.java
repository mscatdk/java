package jms.web.jms;

import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

public class JmsSendAndReceive {
	private final QueueConnection qcon;
	private final QueueSession qsession;
	private final QueueReceiver qreceiver;
	private final QueueSender qsender;
	private final TextMessage msg;
	
	public JmsSendAndReceive(QueueConnection qcon, QueueSession qsession, QueueReceiver qreceiver, QueueSender qsender,
			TextMessage msg) {
		super();
		this.qcon = qcon;
		this.qsession = qsession;
		this.qreceiver = qreceiver;
		this.qsender = qsender;
		this.msg = msg;
	}
	
	public QueueReceiver getQueueReceiver() {
		return qreceiver;
	}
	
	public void send(String message) throws JMSException {
		msg.setText(message);
		qsender.send(msg);
	}

	public void close() throws JMSException {
		qreceiver.close();
		qsender.close();
		qsession.close();
		qcon.close();
	}	
}
