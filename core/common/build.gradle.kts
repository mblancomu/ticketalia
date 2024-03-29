plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    alias(libs.plugins.mobilechallenge.android.hilt)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.navigation.compose)
}