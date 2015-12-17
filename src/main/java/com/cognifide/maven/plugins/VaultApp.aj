package com.cognifide.maven.plugins;

public aspect VaultApp {

	private Exception lastException = null;

	protected pointcut runAction(): call(public void org.apache.jackrabbit.vault.vlt.actions.Action.run(..));

	after() throwing(Exception e): runAction() {
		lastException = e;
	}

	protected pointcut runCogApplication(): call(public static void com.cognifide.maven.plugins.CogVaultFsApp.main(..));

	after(): runCogApplication() {
		if (lastException != null) {
			VltExecutionException vltExecutionException = new VltExecutionException(lastException);
			lastException = null;
			throw vltExecutionException;
		}
	}

}
