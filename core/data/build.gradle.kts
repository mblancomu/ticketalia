plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    alias(libs.plugins.mobilechallenge.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.data"

    buildFeatures {
        buildConfig = true
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.database)
    implementation(projects.core.datastore)
    implementation(projects.core.model)
    implementation(projects.core.network)

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.retrofit.core)

    testImplementation(projects.core.testing)
}