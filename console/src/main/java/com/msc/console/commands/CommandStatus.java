package com.msc.console.commands;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.Ansi.Color;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Print current status")
public class CommandStatus {

	@Parameter(names = "-debug", description = "Enables debug mode")
	private Boolean debug = false;
	
	public void executeCommand() {
    	AnsiConsole.systemInstall();
    	Ansi ansi = new Ansi();
    	
        System.out.println( ansi.fg(Color.RED).a("Running status").reset() );
	}
}
