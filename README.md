# Maveniverse Nisse

This is a near-trivial Maven 3 and 4 extension, that provides following services:
* sources properties from different sources and makes them Maven User Properties
* redoes the [CI Friendly Versions](https://maven.apache.org/maven-ci-friendly.html) feature (not with **Plugin**)
* is able to "inline" properties, so there is no need to flatten or any other mumbo-jumbo, it "just works" (not with **Plugin**)

## Implemented Sources

Currently there are 4 sources just to showcase things:
* file-source: it reads up a Java Properties File
* jgit-source: it uses Eclipse JGit to get some git related data
* mvn-source: it provides major/minor/patch versions of currently used Maven
* os-source: heavily inspired by https://github.com/trustin/os-maven-plugin (and annoyed that user does not maintain it, so code is not reusable nor works with Maven4) 

Look into ITs for examples.

## Usage with Maven3.9.x

Nisse offers a Plugin and a Core Extension. With using Plugin only, features you can use is LIMITED to properties
injection into Project properties (a la [properties-maven-plugin](https://www.mojohaus.org/properties-maven-plugin/) from Mojohaus).
To use the plugin, add it to your POM like this:

```xml
  <plugin>
    <groupId>eu.maveniverse.maven.nisse</groupId>
    <artifactId>plugin3</artifactId>
    <version>${version.nisse}</version>
    <executions>
        <execution>
            <id>inject-properties</id>
            <goals>
                <goal>inject-properties</goal>
            </goals>
            <phase>validate</phase>
        </execution>
    </executions>
  </plugin>
```

The Core Extension is more powerful, to use it add this to your `.mvn/extensions.xml` file:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<extensions>
    <extension>
        <groupId>eu.maveniverse.maven.nisse</groupId>
        <artifactId>extension3</artifactId>
        <version>${version.nisse}</version>
    </extension>
</extensions>
```

And then you can inspect what is being added to user properties by running Toolbox `dump`:

```
$ mvn eu.maveniverse.maven.plugins:toolbox:dump -Dverbose -N
```

Look for "USER PROPERTIES" section. Nisse injected some properties for you as they were user properties.
Moreover, you are free to use them as version (ie. `<version>${nisse.jgit.commit}</version>`) and look
what happens. Oh, and just install/deploy as usual, no need for any mumbo-jumbo.

With extension present, the "CI friendly feature" of Maven is improved as:
* it allows ANY expression in version, as long it is defined (effective POM will not have expression left)
* there is no need to use flatten plugin to install/deploy, as version used expressions are inlined in POM.

## Usage with Maven 4.0.x

Support is on the way... keep eye on `eu.maveniverse.maven.nisse:plugin4` and `eu.maveniverse.maven.nisse:extension4`.
