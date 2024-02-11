plugins {
    id("mobilechallenge.android.library")
    kotlin("kapt")
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.domain"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.runtime)
    api(libs.junit4)
}