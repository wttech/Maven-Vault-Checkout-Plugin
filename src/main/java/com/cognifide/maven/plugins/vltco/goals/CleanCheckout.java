package com.cognifide.maven.plugins.vltco.goals;

import com.cognifide.maven.plugins.vltco.utils.ProjectUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "clean-checkout")
public class CleanCheckout extends Checkout {

	public void execute() throws MojoExecutionException {
		super.execute();
		ProjectUtils.removeVltFiles(destdir);
		ProjectUtils.cleanupDotContent(destdir);
	}

}
