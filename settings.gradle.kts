pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
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
rootProject.name = "mobilechallenge"
include(":app")
include(":core:designsystem")
include(":core:data")
include(":core:domain")
include(":core:database")
include(":core:datastore")
include(":core:network")
include(":core:ui")
include(":core:model")
include(":core:testing")
include(":core:common")
include(":feature:events")
include(":feature:venues")