plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    alias(libs.plugins.mobilechallenge.android.hilt)
    alias(libs.plugins.mobilechallenge.android.room)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.database"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.common)
    implementation(libs.room.paging)

    androidTestImplementation(projects.core.testing)
}