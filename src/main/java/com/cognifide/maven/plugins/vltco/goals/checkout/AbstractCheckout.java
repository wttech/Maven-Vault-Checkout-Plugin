package com.cognifide.maven.plugins.vltco.goals.checkout;

import com.cognifide.maven.plugins.vltco.goals.AbstractVaultMojo;
import com.cognifide.maven.plugins.vltco.vault.VaultApp;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractCheckout extends AbstractVaultMojo {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	protected static final String CHECKOUT_COMMAND = "checkout";

	@Parameter(property = "vltco.filter", defaultValue = "src/main/cq/META-INF/vault/filter.xml")
	protected String filter;

	@Parameter(property = "vltco.uri", defaultValue = "http://localhost:4502", required = true)
	protected String uri;

	@Parameter(property = "vltco.user", defaultValue = "admin")
	protected String user;

	@Parameter(property = "vltco.password", defaultValue = "admin")
	protected String password;

	protected void executeVaultCommand(String command, String... additionalParameters) {
		List<String> parameters = new LinkedList<String>();
		parameters.addAll(
				Arrays.asList(new String[] { "--credentials", user + ":" + password, command, "--force" }));
		Collections.addAll(parameters, additionalParameters);
		log.info("Invoking: vlt {}", parameters);
		new VaultApp().runApp(parameters.toArray(ArrayUtils.EMPTY_STRING_ARRAY));
	}

	protected void runCheckoutCommand() {
		if (new File(filter).exists()) {
			executeVaultCommand(CHECKOUT_COMMAND, "-f", filter, uri, "/", localPath);
		} else {
			executeVaultCommand(CHECKOUT_COMMAND, uri, "/", localPath);
		}
	}
}
