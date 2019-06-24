# JGiven Specification Gradle Plugin

This plugin generates a specification based upon your [Jgiven](http://jgiven.org) test report.

## Gradle Testkit Configuration
The E2E-Tests use the [Gradle TestKit](https://docs.gradle.org/current/userguide/test_kit.html).
The plugin code under test is injected automatically via the [Gradle Plugin development Plugin](https://docs.gradle.org/current/userguide/java_gradle_plugin.html#java_gradle_plugin), which also adds the testkit dependencies.
This plugin generates a plugin-under-test-metadata.properties file with the classpath information required by the gradle test runner. TO make this work in Intellij you have to select Gradle Test Runner in Settings-> Build-> Build Tools->Gradle->Runner See this [Thread](https://stackoverflow.com/questions/44679007/plugin-under-test-metadata-properties-not-created-by-gradle-testkit-when-running) for more details.