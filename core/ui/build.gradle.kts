plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    alias(libs.plugins.mobilechallenge.android.library.compose)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.ui"

    defaultConfig {
        testInstrumentationRunner = "com.manuelblanco.mobilechallenge.core.testing.TicketsTestRunner"
    }
}

dependencies {

    implementation(projects.core.designsystem)
    implementation(projects.core.common)

    implementation(libs.lottie.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.compose.material)

    androidTestImplementation(projects.core.testing)
}