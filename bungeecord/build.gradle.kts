import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("java-library")
    id("com.github.johnrengelman.shadow")
}

dependencies {
    api(project(":paraparty-authorization"))
    compileOnly("net.md-5:bungeecord-api:1.16-R0.4")
}

group = "party.para"
version = "1.0-SNAPSHOT"
description = "Authorization"
java.sourceCompatibility = JavaVersion.VERSION_1_8

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<ShadowJar> {
    dependencies {
        include { it.moduleGroup.startsWith("com.fasterxml") }
        include { it.moduleGroup.startsWith("com.squareup.okhttp3") }
        include { it.moduleGroup.startsWith("com.squareup.okio") }
        include { it.moduleGroup.startsWith("org.jetbrains.kotlin") }
        include { it.moduleGroup == "party.para" && it.moduleName == "paraparty-authorization" }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}
