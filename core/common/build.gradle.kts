plugins {
    id("mobilechallenge.android.library")
    id("mobilechallenge.android.hilt")
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.navigation.compose)
    implementation(project(":core:model"))
    testImplementation(project(":core:testing"))
}