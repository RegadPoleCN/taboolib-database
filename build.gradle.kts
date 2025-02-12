import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `maven-publish`
    java
    kotlin("jvm") version "1.9.25"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.regadpole"
version = "1.0.4-SNAPSHOT"

repositories {
    mavenLocal()
    maven("https://jitpack.io")
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:21.0")
    compileOnly("org.slf4j:slf4j-api:2.0.8")
    implementation("com.zaxxer:HikariCP:4.0.3")
    compileOnly("com.github.alazeprt:AConfiguration:1.0")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
        artifact(tasks["kotlinSourcesJar"])
    }
}