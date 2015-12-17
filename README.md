# Maven Vault Plugin

## Overview

## Installation

Compile and install:

mvn clean install

## Usage

Add plugin to your POM file:

```xml
<plugin>
	<groupId>com.cognifide.maven.plugins</groupId>
	<artifactId>vltco-maven-plugin</artifactId>
	<version>0.2.0</version>
</plugin>
```

### Commands

|| Command description || Maven command || Description ||
| Checkout | mvn vltco:clean-checkout | Wrapper for vlt checkout |
| Clean | mvn vltco:clean-checkout | Removes .vlt files. Cleans content.xml from unnecessary properties |
| Clean checkout | mvn vltco:clean-checkout | Wrapper for vlt checkout. After checkout removes .vlt files. Cleans content.xml from unnecessary properties |

### Configuration

|| Parameter name || Description || Default value ||
| vltco.srcdir | directory for the content to be stored in | src/main/cq/jcr_root |
| vltco.url | instance url | http://localhost:4502 |
| vltco.user | user name | admin |
| vltco.password | user password | admin |
