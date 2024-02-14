plugins {
    id("mobilechallenge.android.feature")
    id("mobilechallenge.android.library.compose")
}

android {
    namespace = "com.manuelblanco.mobilechallenge.feature.venues"
}

dependencies {
    implementation(project(":core:testing"))
    implementation(project(":core:common"))

    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.compose.material)
    implementation(libs.accompanist.drawablepainter)
    testImplementation(libs.mockk)
}