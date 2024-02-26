plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    alias(libs.plugins.mobilechallenge.android.hilt)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.common"
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)

    api(libs.kotlinx.coroutines.test)
    api(libs.junit4)

    testImplementation(projects.core.testing)
}