package com.msc.console;

import com.beust.jcommander.JCommander;
import com.msc.console.commands.CommandLoad;
import com.msc.console.commands.CommandStatus;

/**
 * Demo application showing how to use JCommander to parse command line arguments.
 * The application also show how to print text in different colors.
 * @author admin
 *
 */
public class App {
	public static void main(String[] args) {
		CommandMain cm = new CommandMain();
		JCommander jc = new JCommander(cm);

		CommandStatus commandStatus = new CommandStatus();
		jc.addCommand("status", commandStatus);

		CommandLoad commandLoad = new CommandLoad();
		jc.addCommand("load", commandLoad);

		jc.setProgramName("DataLoader");
		jc.parse(args);

		if (jc.getParsedCommand() == null) {
			jc.usage();
		} else {
			switch (jc.getParsedCommand()) {
			case "status":
				commandStatus.executeCommand();
				break;
			case "load":
				commandLoad.executeCommand();
				break;
			default:
				jc.usage();
			}
		}
	}
}
