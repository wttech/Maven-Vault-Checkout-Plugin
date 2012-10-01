package com.cognifide.maven.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;

public class ProjectUtils {

	public static void removeVltFiles(String root) {
		for (File file : FileUtils.listFiles(new File(root), new NameFileFilter(".vlt"),
				TrueFileFilter.INSTANCE)) {
			System.out.println("Deleting " + file.getPath());
			FileUtils.deleteQuietly(file);
		}
	}

	public static void cleanupDotContent(String root) throws MojoExecutionException {
		for (File file : FileUtils.listFiles(new File(root), new NameFileFilter(".content.xml"),
				TrueFileFilter.INSTANCE)) {

			try {
				List<String> lines = new ArrayList<String>();
				for (String line : (List<String>) FileUtils.readLines(file, "UTF-8")) {
					String cleanLine = StringUtils.trimToEmpty(line);
					// System.out.println(cleanLine);
					String[] properties = { "jcr:lastModified", "jcr:created", "cq:lastModified",
							"cq:lastReplicat", "jcr:uuid" };
					boolean lineContains = lineContainsProperty(cleanLine, properties);
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
				FileUtils.writeLines(file, "UTF-8", lines);
			} catch (IOException e) {
				throw new MojoExecutionException(String.format("Error opening %s", file.getPath()), e);
			}

		}
	}

	private static boolean lineContainsProperty(String cleanLine, String[] properties) {
		boolean contains = false;
		for (String property : properties) {
			if (cleanLine.startsWith(property)) {
				contains = true;
			}
		}
		return contains;
	}

}
