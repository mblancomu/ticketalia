plugins {
    alias(libs.plugins.mobilechallenge.android.feature)
    alias(libs.plugins.mobilechallenge.android.library.compose)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.feature.favorites"
}