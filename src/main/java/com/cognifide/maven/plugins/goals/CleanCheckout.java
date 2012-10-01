package com.cognifide.maven.plugins.goals;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import com.cognifide.maven.utils.ProjectUtils;

@Mojo(name = "clean-checkout")
public class CleanCheckout extends Checkout {

	public void execute() throws MojoExecutionException {
		super.execute();
		ProjectUtils.removeVltFiles(srcdir);
		ProjectUtils.cleanupDotContent(srcdir);
	}

}
