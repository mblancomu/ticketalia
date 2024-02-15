plugins {
    id("mobilechallenge.android.feature")
    id("mobilechallenge.android.library.compose")
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.feature.venues"

}

dependencies {
    androidTestImplementation(project(":core:testing"))
    testImplementation(project(":core:testing"))
    implementation(project(":core:common"))

    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.compose.material)
    implementation(libs.accompanist.drawablepainter)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)
    testImplementation(libs.hilt.android.testing)
}