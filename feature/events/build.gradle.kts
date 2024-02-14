plugins {
    id("mobilechallenge.android.feature")
    id("mobilechallenge.android.library.compose")
}

android {
    namespace = "com.manuelblanco.mobilechallenge.feature.events"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:testing"))
    implementation(project(":core:domain"))
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.compose.bom)
    implementation(libs.androidx.compose.material)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
}