package jms.web.jms;

import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;

public class JmsReceive implements AutoCloseable {
	private final QueueConnection qcon;
	private final QueueSession qsession;
	private final QueueReceiver qreceiver;

	public JmsReceive(QueueConnection qcon, QueueSession qsession, QueueReceiver qreceiver) {
		this.qcon = qcon;
		this.qsession = qsession;
		this.qreceiver = qreceiver;
	}
	
	public QueueReceiver getQueueReceiver() {
		return qreceiver;
	}

	public void close() throws JMSException {
		qreceiver.close();
		qsession.close();
		qcon.close();
	}
}
