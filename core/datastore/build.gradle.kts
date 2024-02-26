plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    alias(libs.plugins.mobilechallenge.android.hilt)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.datastore"
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(libs.androidx.dataStore.preferences)
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(projects.core.testing)
}