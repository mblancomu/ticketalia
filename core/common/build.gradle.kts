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
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)
    api(libs.kotlinx.coroutines.test)

    implementation(project(":core:model"))
    testImplementation(project(":core:testing"))
    api(libs.junit4)
}