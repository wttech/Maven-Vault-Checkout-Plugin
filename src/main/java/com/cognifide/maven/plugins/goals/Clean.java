package com.cognifide.maven.plugins.goals;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import com.cognifide.maven.plugins.AbstractVaultMojo;
import com.cognifide.maven.utils.ProjectUtils;

@Mojo(name = "clean")
public class Clean extends AbstractVaultMojo {

	public void execute() throws MojoExecutionException {
		ProjectUtils.removeVltFiles(srcdir);
		ProjectUtils.cleanupDotContent(srcdir);
	}

}
