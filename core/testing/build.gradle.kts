import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("mobilechallenge.android.library")
    id("mobilechallenge.android.library.compose")
    id("mobilechallenge.android.hilt")
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.testing"
}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))

    api(libs.androidx.compose.ui.test)
    api(libs.androidx.test.core)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
    api(libs.hilt.android.testing)
    api(libs.junit4)
    api(libs.kotlinx.coroutines.test)

    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)

    debugApi(libs.androidx.compose.ui.testManifest)
    implementation(libs.androidx.activity.compose)

    implementation(libs.kotlinx.datetime)
    implementation(libs.robolectric)
    api(libs.roborazzi)
    implementation(libs.accompanist.testharness)
}
