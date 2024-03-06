plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    alias(libs.plugins.mobilechallenge.android.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.domain"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.androidx.paging.runtime)
}