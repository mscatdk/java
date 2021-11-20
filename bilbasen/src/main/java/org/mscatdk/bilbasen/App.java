package org.mscatdk.bilbasen;

import org.mscatdk.bilbasen.commands.CommandDownload;
import org.mscatdk.bilbasen.commands.CommandInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;

public class App {
	
	private static Logger console = LoggerFactory.getLogger("console");
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args ) {
    	JCommander jc = new JCommander();
    	jc.addCommand("download", new CommandDownload());
    	
		jc.setProgramName("Bilbasen Util");
		
		try {
			jc.parse(args);
			String parsedCommand = jc.getParsedCommand();
			JCommander parsedJCommander = jc.getCommands().get(parsedCommand);
			if (parsedJCommander != null) {
				((CommandInterface) parsedJCommander.getObjects().get(0)).exec();
			} else {
				jc.usage();
			}
			console.info("Done");
		} catch (Exception e) {
			console.info("Expection with message {}", e.getMessage());
			logger.error("unhandled exception.", e);
			jc.usage();
		}
    }

}