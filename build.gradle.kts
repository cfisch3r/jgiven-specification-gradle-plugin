plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.21")
    `java-gradle-plugin`
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.asciidoctor:asciidoctorj:2.1.0")
    implementation("org.jtwig:jtwig-core:5.87.0.RELEASE")

    implementation(gradleApi())
    implementation(localGroovy())

    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.mockito:mockito-core:2.+")
    testImplementation("org.mockito:mockito-junit-jupiter:2.24.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
