package com.cognifide.maven.plugins.vltco.goals;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

public abstract class AbstractVaultMojo extends AbstractMojo {

	@Parameter(property = "vltco.localPath", defaultValue = "src/main/cq/jcr_root/")
	protected String localPath;
}
