plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    kotlin("kapt")
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.domain"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.runtime)

    api(libs.junit4)
}