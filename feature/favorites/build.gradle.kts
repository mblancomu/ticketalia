plugins {
    alias(libs.plugins.mobilechallenge.android.feature)
    alias(libs.plugins.mobilechallenge.android.library.compose)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.feature.favorites"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.ui)
    implementation(projects.core.common)

    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.compose.bom)
}