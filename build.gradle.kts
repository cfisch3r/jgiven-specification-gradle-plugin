plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.21")
    `java-gradle-plugin`
}

repositories {
    jcenter()
}

sourceSets {
    val samples by creating {
        java.srcDir("src/samples/java")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.asciidoctor:asciidoctorj:2.1.0")
    implementation("org.jtwig:jtwig-core:5.87.0.RELEASE")
    implementation("com.google.code.gson:gson:2.8.5")

    implementation(gradleApi())
    implementation(localGroovy())

    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.mockito:mockito-core:2.+")
    testImplementation("org.mockito:mockito-junit-jupiter:2.24.0")

    "samplesImplementation"("org.junit.jupiter:junit-jupiter:5.4.2")
    "samplesImplementation"("com.tngtech.jgiven:jgiven-junit5:0.17.1")
    constraints {
        "samplesImplementation"("net.bytebuddy:byte-buddy:1.9.13") {
            because("previous versions were not compatible with java 12/13")
        }
    }

}

tasks.register<Test>("generateSamples") {
    description = "generate test output."
    group = "verification"
    testClassesDirs = sourceSets["samples"].output.classesDirs
    classpath = sourceSets["samples"].runtimeClasspath
    mustRunAfter(tasks["test"])
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
