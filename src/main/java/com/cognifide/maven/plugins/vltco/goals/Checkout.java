package com.cognifide.maven.plugins.vltco.goals;

import java.io.File;

import com.cognifide.maven.plugins.vltco.plugins.AbstractVaultMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "checkout")
public class Checkout extends AbstractVaultMojo {

	@Parameter(property = "vltco.filter", defaultValue = "src/main/cq/META-INF/vault/filter.xml")
	protected String filter;

	public void execute() throws MojoExecutionException {
		if (new File(filter).exists()) {
			executeVaultCommand("checkout", "-f", filter, url, "/", destdir);
		} else {
			executeVaultCommand("checkout", url, "/", destdir);
		}
	}

}
