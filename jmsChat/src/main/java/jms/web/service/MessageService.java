package jms.web.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jms.web.common.AppContext;
import jms.web.common.Config;
import jms.web.common.Message;
import jms.web.jms.JmsManager;
import jms.web.jms.JmsReceive;
import jms.web.jms.JmsSend;

@WebServlet(name="MessageService", urlPatterns={"/messageService"})
public class MessageService extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1190345934137683747L;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		List<String> messageList = new ArrayList<>();
		JmsManager jmsManager = AppContext.getBean("jmsManager", JmsManager.class);
		String id = req.getSession().getId();
		System.out.println("Session id: " + id);
		
		try (JmsReceive jmsReceive = jmsManager.getJmsListener(Config.JMS_FACTORY, Config.QUEUE, id)) {
			javax.jms.Message message;
			while ((message = jmsReceive.getQueueReceiver().receive(100)) != null) {
				messageList.add(((TextMessage)message).getText());
			}
			
		} catch (Exception e) {
			messageList.add("JMS error");
		}
		
		
		pw.write(new Gson().toJson(messageList));		
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Gson gson = new Gson();
		Message msg = gson.fromJson(req.getReader(), Message.class);
		String id = req.getSession().getId();
		
		System.out.println("Session id: " + id);
		
		if (msg != null && msg.getText() != null && !msg.getText().isEmpty()) {
			JmsManager jmsManager = AppContext.getBean("jmsManager", JmsManager.class);
			try (JmsSend jmsSend = jmsManager.getJmsSend(Config.JMS_FACTORY, Config.QUEUE)) {
				jmsSend.send(msg.getText(), id);
			} catch (Exception e) {
				res.sendError(500);
			}
		} else {
			res.sendError(400);
		}
		
    }

	
	
}
