rootProject.name = "farmersdelight"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://repo.xenondevs.xyz/releases/")
    }
    versionCatalogs {
        create("libs") {
            from("xyz.xenondevs.nova:catalog:0.19-RC.3")
        }
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://repo.xenondevs.xyz/releases/")
    }
}