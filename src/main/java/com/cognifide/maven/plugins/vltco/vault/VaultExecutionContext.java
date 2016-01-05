package com.cognifide.maven.plugins.vltco.vault;

import org.apache.commons.cli2.CommandLine;

import org.apache.jackrabbit.vault.cli.VltExecutionContext;
import org.apache.jackrabbit.vault.util.console.CliCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

public class VaultExecutionContext extends VltExecutionContext {

	private static final Logger log = LoggerFactory.getLogger(VaultExecutionContext.class);

	public VaultExecutionContext(VaultApp app) {
		super(app);
	}

	@Override
	public boolean execute(CommandLine commandLine) {
		for (Object command : this.commands) {
			if (command instanceof CliCommand) {
				CliCommand cliCommand = (CliCommand) command;
				try {
					if (doExecute(cliCommand, commandLine)) {
						return true;
					}
				}
				catch (ExecutionException ex) {
					log.error("TBC", ex);
					throw new VaultExecutionException(ex);
				}
				catch (Exception e) {
					log.error("TBC", e);
					throw new VaultExecutionException(e);
				}
			}
		}
		return false;
	}
}
