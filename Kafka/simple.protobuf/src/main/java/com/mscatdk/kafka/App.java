package com.mscatdk.kafka;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.mscatdk.kafka.command.Command;
import com.mscatdk.kafka.command.GetCommand;
import com.mscatdk.kafka.command.PutCommand;

public class App  {
	
	@Parameter(names = { "-h", "--host"}, description = "Kafka Broker/s")
	private String host;
	
	@Parameter(names = { "-t", "--topic"}, description = "Kafka topic")
	private String topic;
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	private static final Logger console = LoggerFactory.getLogger("console");
	
    public static void main( String[] args ) {
        Properties properties = new Properties();
        App app = new App();
        
    	JCommander jc = JCommander.newBuilder()
    			.addObject(app)
    		    .addCommand("put", new PutCommand())
    		    .addCommand("get", new GetCommand())
    		    .build();
    	
    	try {
        	jc.setProgramName("Kafka Demo");
    		jc.parse(args);
    		
    		Command command = getCommand(jc);
    		
    		properties.put("bootstrap.servers", app.host);
    		command.exec(properties, app.topic);
    	} catch (ParameterException e) {
    		jc.usage();
    		logger.error("Unable to parse arguments!", e);
    		console.error("Unhandled Exception. Please see logs for details!");    		
    		System.exit(-1);
    	} catch (Exception ex) {
    		logger.error("Application error!", ex);
    		console.error("Unhandled Exception. Please see logs for details!");
    		System.exit(-1);
    	}

    }
    
    private static Command getCommand(JCommander jc) {
    	JCommander cmd = jc.getCommands().get(jc.getParsedCommand());
    	if (cmd == null || cmd.getObjects().size() != 1) {
    		throw new IllegalStateException("Unable to parse command!");
    	}
    	
    	Object obj = cmd.getObjects().get(0);
    	if (!(obj instanceof Command)) {
    		throw new IllegalStateException("cmd.getObjects().get(0) object doesn't implement the Command interface!");
    	}
    	return (Command) obj;
    }
}
