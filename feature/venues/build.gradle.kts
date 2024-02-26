plugins {
    alias(libs.plugins.mobilechallenge.android.feature)
    alias(libs.plugins.mobilechallenge.android.library.compose)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.feature.venues"

}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.testing)

    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.compose.material)
    implementation(libs.accompanist.drawablepainter)

    testImplementation(projects.core.testing)
    testImplementation(libs.hilt.android.testing)

    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(projects.core.testing)
}
