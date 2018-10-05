package com.cognifide.maven.plugins.vltco.goals.clean;

import com.cognifide.maven.plugins.vltco.goals.AbstractVaultMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "clean")
public class Clean extends AbstractVaultMojo {

	@Override
	public void execute() throws MojoExecutionException {
		CleanHelper.removeVltFiles(localPath);
		CleanHelper.cleanupDotContent(localPath, lineEnding,  contentProperties);
	}
}
