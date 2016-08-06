package com.msc.console.commands;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import com.beust.jcommander.Parameters;

import org.fusesource.jansi.Ansi.Color;

@Parameters(commandDescription = "Load the specified data")
public class CommandLoad {
	
	public void executeCommand() {
    	AnsiConsole.systemInstall();
    	Ansi ansi = new Ansi();
    	
        System.out.println( ansi.fg(Color.GREEN).a("Running load").reset() );
	}

}
