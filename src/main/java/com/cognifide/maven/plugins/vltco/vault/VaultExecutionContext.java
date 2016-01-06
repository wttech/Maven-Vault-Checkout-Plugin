package com.cognifide.maven.plugins.vltco.vault;

import org.apache.commons.cli2.CommandLine;

import org.apache.jackrabbit.vault.cli.VltExecutionContext;
import org.apache.jackrabbit.vault.util.console.CliCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

class VaultExecutionContext extends VltExecutionContext {

	private static final Logger LOG = LoggerFactory.getLogger(VaultExecutionContext.class);

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
				} catch (ExecutionException ex) {
					LOG.error("Error while executing command", ex);
					throw new VaultExecutionException(ex);
				} catch (Exception e) {
					LOG.error("Error while executing command", e);
					throw new VaultExecutionException(e);
				}
			}
		}
		return false;
	}
}
