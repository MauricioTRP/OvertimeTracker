pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

rootProject.name = "OTT_multimodule"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":auth:domain")
include(":auth:presentation")
include(":core:domain")
include(":core:data")
include(":core:database")
include(":core:presentation:designsystem")
include(":core:presentation:ui")
include(":auth:data")
include(":workhours:domain")
include(":workhours:data")
include(":workhours:presentation")
include(":booking:domain")
include(":booking:data")
include(":booking:presentation")
include(":booking:database")
include(":booking:network")
