plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    alias(libs.plugins.mobilechallenge.android.library.compose)
}

android {
    defaultConfig {
        testInstrumentationRunner = "com.manuelblanco.mobilechallenge.core.testing.TicketsTestRunner"
    }
    namespace = "com.manuelblanco.mobilechallenge.core.designsystem"
}

dependencies {

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)

    debugApi(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.palette)

    androidTestImplementation(projects.core.testing)
}