plugins {
    id("mobilechallenge.android.feature")
    id("mobilechallenge.android.library.compose")
    alias(libs.plugins.paparazzi)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.feature.venues"

}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.compose.material)
    implementation(libs.accompanist.drawablepainter)

    testImplementation(project(":core:testing"))
    testImplementation(libs.robolectric)
    testImplementation(libs.hilt.android.testing)

    androidTestImplementation(project(":core:testing"))
}
