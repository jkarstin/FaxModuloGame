# Fax Modulo

## Build & Run
The easiest way to run the current version of the game is to install Java 8 JRE
and ensure that the environment variable JAVA_HOME is associated with your JRE
installation location.

Once that is complete, clone the repository, open a new Command Prompt window,
and navigate to the root location of the repository. Once there run the command

```
gradlew desktop:run
```

If everything is installed correctly, an instance of the game should start up.

## Platform Requirements

- Any hardware that can run Java 8 JRE

This game is written for Java 8 SE Update 221 (jre1.8.0_221), though from
observation any version of Java 8 JRE should be able to run the code. This
requirement comes from the reliance on the LibGDX library for graphical support.
