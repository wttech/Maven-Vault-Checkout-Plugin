package com.cognifide.maven.plugins.vltco.goals.checkout;

import com.cognifide.maven.plugins.vltco.goals.clean.CleanHelper;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "clean-checkout")
public class CleanCheckout extends AbstractCheckout {

	@Override
	public void execute() throws MojoExecutionException {
		runCheckoutCommand();
		CleanHelper.removeVltFiles(localPath);
		CleanHelper.cleanupDotContent(localPath, lineEnding,contentProperties);
	}
}
