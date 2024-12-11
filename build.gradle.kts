plugins {
    `maven-publish`
    java
    kotlin("jvm") version "2.0.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "taboolib"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compileOnly("com.google.guava:guava:21.0")
    compileOnly("org.slf4j:slf4j-api:2.0.8")
    compileOnly("com.zaxxer:HikariCP:4.0.3")
    compileOnly("org.spongepowered:configurate-core:4.1.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
        artifact(tasks["kotlinSourcesJar"])
    }
}