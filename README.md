![Cognifide logo](http://cognifide.github.io/images/cognifide-logo.png)
# Maven Vault Checkout Plugin

## Purpose
This is a plugin designed for Maven 2.x+ based builds to automate content checkout process.
It allows to set up content checkout, and remove some unnecessary properties from .content.xml files.

## Features
* downloads content(using vlt) from CQ/AEM instance
* removes .vlt files
* removes properties from .content.xml files

## Prerequisites
* Maven 2.x, 3.x

## Installation
Checkout the source code:

    cd [folder of your choice]
    git clone git://github.com/Cognifide/Maven-Vault-Checkout-Plugin.git
    cd Maven-Vault-Checkout-Plugin

Compile and install:

    mvn clean install

## Usage
Set up POM file:

```xml
    (...)
    <plugin>
        <groupId>com.cognifide.maven.plugins</groupId>
        <artifactId>vltco-maven-plugin</artifactId>
        <version>0.2.0</version>
        <configuration>
            <uri>http://localhost:4503</uri>
            <user>author</user>
            <password>author</password>
            <lineEnding>unix</lineEnding>
            <localPath>src/main/content/jcr_root</localPath>
            <filter>src/main/content/jcr_root</filter>
            <!-- custom content properties that will be removed from .content.xml during clean goal -->
            <contentProperties>
                <contentProperty>my:contentProperty</contentProperty>
            </contentProperties>
        </configuration>
    </plugin>
    (...)
```

Now you can invoke one of the Maven Vault Checkout Plugin goals:
* to checkout content from instance use

        mvn vltco:checkout

* to remove .vlt files and cleans content.xml from unnecessary properties use (default properties are: jcr:uuid, jcr:lastModified, jcr:lastModifiedBy, jcr:created, jcr:createdBy, cq:lastModified, cq:lastModifiedBy, cq:lastReplicated, cq:lastReplicatedBy, cq:lastReplicationAction, cq:lastReplicationStatus)

        mvn vltco:clean

* to checkout and remove .vlt files and cleans content.xml from unnecessary properties use

        mvn vltco:clean-checkout

## Configuration
Maven Vault Checkout Plugin can be configured using <configuration> element (see Usage sample above) with following tags:

| Parameter name | Default value | Description |
|----------------|---------------|-------------|
| uri | http://localhost:4502 | instance uri |
| user | admin | user name |
| password | admin | user password |
| localPath | src/main/aem/jcr_root | directory for the content to be stored in |
| filter | src/main/aem/META-INF/vault/filter.xml | filter file location |
| lineEnding | default | can be unix, dos, defualt is system default|
| contentProperties | jcr:uuid, jcr:lastModified, jcr:lastModifiedBy, jcr:created, jcr:createdBy, cq:lastModified, cq:lastModifiedBy, cq:lastReplicated, cq:lastReplicatedBy, cq:lastReplicationAction, cq:lastReplicationStatus | list of properties that will be remove from .content.xml files during clean goal |

## Commercial Support
Technical support can be made available if needed. Please [contact us](mailto:labs-support@cognifide.com) for more details.

We can:
* prioritize your feature request,
* tailor the product to your needs,
* provide a training for your engineers,
* support your development teams.