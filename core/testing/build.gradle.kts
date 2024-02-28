plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    alias(libs.plugins.mobilechallenge.android.library.compose)
    alias(libs.plugins.mobilechallenge.android.hilt)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.testing"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(projects.core.common)

    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.robolectric.shadows)
    implementation(libs.kotlinx.datetime)
    implementation(libs.accompanist.testharness)

    api(kotlin("test"))
    api(libs.androidx.compose.ui.test)
    api(libs.androidx.test.core)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
    api(libs.hilt.android.testing)
    api(libs.junit4)
    api(libs.kotlinx.coroutines.test)
    api(libs.androidx.navigation.testing)
    api(libs.androidx.test.ext)

    debugApi(libs.androidx.compose.ui.testManifest)
}
