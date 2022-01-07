pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
}

rootProject.name = "paraparty-authorization"

val modules = listOf(
    "bungeecord",
    "forge-1_16_5",
    "paraparty-authorization",
)

for (item in modules) {
    include(":$item")
}

for (item in modules) {
    project(":$item").projectDir = file("$rootDir/$item")
}
