plugins {
    id("java")
    id("java-library")
}

group = "party.para"
version = "1.0-SNAPSHOT"
description = "Authorization"

repositories {
    mavenCentral()
}

dependencies {
    api("com.squareup.okhttp3:okhttp:4.9.3")
    api("com.fasterxml.jackson.core:jackson-databind:3.0.0-SNAPSHOT")

    api("org.jetbrains:annotations:22.0.0")
}
