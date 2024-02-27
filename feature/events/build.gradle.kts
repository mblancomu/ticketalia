plugins {
    alias(libs.plugins.mobilechallenge.android.feature)
    alias(libs.plugins.mobilechallenge.android.library.compose)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.feature.events"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.core.testing)

    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.compose.bom)
    implementation(libs.androidx.compose.material)

    debugImplementation(libs.androidx.compose.ui.testManifest)

    testImplementation(projects.core.testing)

    androidTestImplementation(projects.core.testing)
}