package com.cognifide.maven.plugins.vltco.vault;

import org.apache.jackrabbit.vault.cli.VaultFsApp;
import org.apache.jackrabbit.vault.util.console.ExecutionContext;
import org.apache.jackrabbit.vault.util.console.commands.CmdConsole;

public class VaultApp extends VaultFsApp {

	private ExecutionContext defaultContext;

	public void runApp(String[] args) {
		run(args);
	}

	@Override
	protected ExecutionContext getDefaultContext() {
		if(defaultContext == null) {
			defaultContext = new VaultExecutionContext(this);
			defaultContext.installCommand(new CmdConsole());
		}

		return defaultContext;
	}
}
