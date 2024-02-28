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
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.compose.material)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)

    debugApi(libs.androidx.compose.ui.tooling)

    androidTestImplementation(projects.core.testing)
}