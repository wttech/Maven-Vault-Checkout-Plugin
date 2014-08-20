package com.cognifide.maven.plugins;

import org.apache.commons.cli2.CommandLine;
import org.apache.commons.cli2.Group;
import org.apache.commons.cli2.Option;
import org.apache.commons.cli2.OptionException;
import org.apache.commons.cli2.builder.ArgumentBuilder;
import org.apache.commons.cli2.builder.DefaultOptionBuilder;
import org.apache.commons.cli2.builder.GroupBuilder;
import org.apache.commons.cli2.commandline.Parser;
import org.apache.jackrabbit.vault.cli.VaultFsApp;
import org.apache.jackrabbit.vault.cli.extended.ExtendedOption;
import org.apache.jackrabbit.vault.cli.extended.XDavEx;
import org.apache.jackrabbit.vault.cli.extended.XJcrLog;
import org.apache.jackrabbit.vault.util.console.CliCommand;
import org.apache.jackrabbit.vault.util.console.ExecutionException;
import org.apache.jackrabbit.vault.util.console.util.Log4JConfig;

/**
 * Author: mariusz.kubis Date: 20.08.14
 */
public class CogVaultFsApp extends VaultFsApp {

	private Option optLogLevel;

	private Option optVersion;

	private Option optHelp;

	private Option optCreds;

	private Option optConfig;

	private Option optUpdateCreds;

	private Exception lastException = null;

	private ExtendedOption[] xOpts = new ExtendedOption[] { new XJcrLog(), new XDavEx() };

	public Group getApplicationCLGroup() {
		return new GroupBuilder().withName("").withOption(addApplicationOptions(new GroupBuilder()).create())
				.withOption(getDefaultContext().getCommandsGroup()).withMinimum(0).create();
	}

	public GroupBuilder addApplicationOptions(GroupBuilder gbuilder) {
		final DefaultOptionBuilder obuilder = new DefaultOptionBuilder();
		final ArgumentBuilder abuilder = new ArgumentBuilder();

		optVersion = obuilder.withLongName("version")
				.withDescription("print the version information and exit").create();
		optHelp = obuilder.withShortName("h").withLongName("help").withDescription("print this help")
				.withArgument(abuilder.withName("command").withMaximum(1).create()).create();

		optLogLevel = obuilder.withLongName("log-level").withDescription("the log4j log level")
				.withArgument(abuilder.withName("level").withMaximum(1).create()).create();

		gbuilder.withName("Global options:")
				// .withOption(optPropertyFile)
				.withOption(CliCommand.OPT_VERBOSE).withOption(CliCommand.OPT_QUIET).withOption(optVersion)
				.withOption(optLogLevel).withOption(optHelp).withMinimum(0);

		optCreds = new DefaultOptionBuilder()
				.withLongName("credentials")
				.withDescription("The default credentials to use")
				.withArgument(
						new ArgumentBuilder()
								.withDescription(
										"Format: <user:pass>. If missing an anonymous login is used. "
												+ "If the password is not specified it is prompted via console.")
								.withMinimum(0).withMaximum(1).create()).create();

		optUpdateCreds = new DefaultOptionBuilder()
				.withLongName("update-credentials")
				.withDescription(
						"if present the credentials-to-host list is updated in the ~/.vault/auth.xml")
				.create();
		optConfig = new DefaultOptionBuilder()
				.withLongName("config")
				.withDescription("The JcrFs config to use")
				.withArgument(
						new ArgumentBuilder().withDescription("If missing the default config is used.")
								.withMinimum(0).withMaximum(1).create()).create();

		// register extended options
		for (ExtendedOption x : xOpts) {
			gbuilder.withOption(x.getOption());
		}
		gbuilder.withOption(optCreds);
		gbuilder.withOption(optUpdateCreds);
		gbuilder.withOption(optConfig);
		return gbuilder;
	}

	public static void main(String[] args) {
		new CogVaultFsApp().run(args);
	}

	protected void run(String[] args) {
		// setup logging
		try {
			initLogging();
		} catch (Throwable e) {
			System.err.println("Error while initializing logging: " + e);
		}

		// setup and start
		init();

		Parser parser = new Parser();
		parser.setGroup(getApplicationCLGroup());
		parser.setHelpOption(optHelp);
		try {
			CommandLine cl = parser.parse(args);
			String logLevel = getEnv().getProperty(KEY_LOGLEVEL);
			if (cl.hasOption(optLogLevel)) {
				logLevel = (String) cl.getValue(optLogLevel);
			}
			if (logLevel != null) {
				Log4JConfig.setLevel(logLevel);
			}
			prepare(cl);
			execute(cl);
		} catch (OptionException e) {
			lastException = e;
			log.error("{}. Type --help for more information.", e.getMessage());
		} catch (ExecutionException e) {
			lastException = e;
			log.error("Error while starting: {}", e.getMessage());
		} finally {
			if (lastException != null) {
				VltExecutionException vltExecutionException = new VltExecutionException(lastException);
				lastException = null;
				throw vltExecutionException;
			}
			close();
		}
	}
}
