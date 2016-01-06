package com.cognifide.maven.plugins.vltco.goals.checkout;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "checkout")
public class Checkout extends AbstractCheckout {

	@Override
	public void execute() throws MojoExecutionException {
		runCheckoutCommand();
	}
}
