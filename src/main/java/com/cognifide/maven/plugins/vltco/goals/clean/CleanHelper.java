package com.cognifide.maven.plugins.vltco.goals.clean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CleanHelper {

	private static final Logger LOG = LoggerFactory.getLogger(CleanHelper.class);

	private CleanHelper() {
	}

	public static void removeVltFiles(String root) {
		for (File file : FileUtils.listFiles(new File(root), new NameFileFilter(".vlt"),
				TrueFileFilter.INSTANCE)) {
			LOG.info("Deleting {}", file.getPath());
			FileUtils.deleteQuietly(file);
		}
	}

	public static void cleanupDotContent(String root, String[] contentProperties) throws MojoExecutionException {
		for (File file : FileUtils.listFiles(new File(root), new NameFileFilter(".content.xml"),
				TrueFileFilter.INSTANCE)) {
			try {
				LOG.info("Cleaning up {}", file.getPath());
				List<String> lines = new ArrayList<String>();
				for (String line : FileUtils.readLines(file, CharEncoding.UTF_8)) {
					String cleanLine = StringUtils.trimToEmpty(line);
					boolean lineContains = lineContainsProperty(cleanLine, contentProperties);
					if (lineContains) {
						if (!cleanLine.endsWith(">")) {
						} else {
							String lastLine = lines.remove(lines.size() - 1);
							lines.add(lastLine + ">");
						}
					} else {
						lines.add(line);
					}
				}
				FileUtils.writeLines(file, CharEncoding.UTF_8, lines);
			} catch (IOException e) {
				throw new MojoExecutionException(String.format("Error opening %s", file.getPath()), e);
			}
		}
	}

	private static boolean lineContainsProperty(String cleanLine, String[] contentProperties) {
		boolean contains = false;
		for (String contentProperty : contentProperties) {
			if (cleanLine.startsWith(contentProperty)) {
				contains = true;
			}
		}
		return contains;
	}
}
