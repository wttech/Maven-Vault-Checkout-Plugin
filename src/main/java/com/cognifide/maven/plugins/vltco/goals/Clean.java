package com.cognifide.maven.plugins.vltco.goals;

import com.cognifide.maven.plugins.vltco.plugins.AbstractVaultMojo;
import com.cognifide.maven.plugins.vltco.utils.ProjectUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "clean")
public class Clean extends AbstractVaultMojo {

	public void execute() throws MojoExecutionException {
		ProjectUtils.removeVltFiles(destdir);
		ProjectUtils.cleanupDotContent(destdir);
	}

}
