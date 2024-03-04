plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    alias(libs.plugins.mobilechallenge.android.library.compose)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.designsystem"
}

dependencies {

    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.palette)
    implementation(libs.androidx.core.ktx)

    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)

    debugApi(libs.androidx.compose.ui.tooling)
}