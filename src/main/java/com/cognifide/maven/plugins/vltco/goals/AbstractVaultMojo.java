package com.cognifide.maven.plugins.vltco.goals;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

public abstract class AbstractVaultMojo extends AbstractMojo {

	@Parameter(property = "vltco.localPath", defaultValue = "src/main/aem/jcr_root")
	protected String localPath;
	
	@Parameter(property = "vltco.lineEnding", defaultValue = "default")
	protected String lineEnding;

	@Parameter(property = "vltco.contentProperties")
	protected String[] contentProperties = { "jcr:lastModified", "jcr:created", "cq:lastModified", "cq:lastReplicat",
			"jcr:uuid" };

	public void setContentProperties(String[] contentProperties) {
		if (ArrayUtils.isNotEmpty(contentProperties)) {
			this.contentProperties = contentProperties;
		}
	}
}