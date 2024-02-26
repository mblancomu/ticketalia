import java.util.Properties

plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    alias(libs.plugins.mobilechallenge.android.hilt)
    id("kotlinx-serialization")
}

val hostUrl: String = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir).getProperty("HOST_URL")
val apiKey: String = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir).getProperty("CONSUMER_KEY")
val secretKey: String = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir).getProperty("CONSUMER_SECRET")

android {
    namespace = "com.manuelblanco.mobilechallenge.core.network"
    compileSdk = 33

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "HOST_URL", hostUrl)
            buildConfigField("String", "CONSUMER_KEY", apiKey)
            buildConfigField("String", "CONSUMER_SECRET", secretKey)
        }

        release {
            buildConfigField("String", "HOST_URL", hostUrl)
            buildConfigField("String", "CONSUMER_KEY", apiKey)
            buildConfigField("String", "CONSUMER_SECRET", secretKey)
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)

    testImplementation(projects.core.testing)
}