package com.msc.console;

import com.beust.jcommander.Parameter;

public class CommandMain {

	@Parameter(names = { "-log", "-verbose" }, description = "Level of verbosity")
	private Integer verbose = 1;
}
