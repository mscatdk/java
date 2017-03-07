package com.msc.servlet3.process;

import java.util.logging.Logger;

public class TestProcess implements Runnable {
	
	private final static Logger LOGGER = Logger.getLogger(TestProcess.class.getName());

	public TestProcess(String id) {
		super();
		this.id = id;
	}

	private final String id;

	@Override
	public void run() {
		LOGGER.info("start");
		for (int i=0; i<5; i++) {
			LOGGER.info("Message " + i);
			SharedMemory.addMessage(id, "Message " + i);
			try {
				Thread.sleep(10*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LOGGER.info("end");
	}

}
