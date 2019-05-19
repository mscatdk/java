package com.mscatdk.kafka.command;

import java.util.Properties;

public interface Command {
	
	void exec(Properties properties, String topic) throws Exception;

}
