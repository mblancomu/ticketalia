plugins {
    id("mobilechallenge.android.feature")
    id("mobilechallenge.android.library.compose")
}

android {
    namespace = "com.manuelblanco.mobilechallenge.feature.favorites"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.compose.bom)
}