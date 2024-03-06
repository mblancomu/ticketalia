plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    alias(libs.plugins.mobilechallenge.android.hilt)
    alias(libs.plugins.mobilechallenge.android.room)
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.database"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)

    implementation(libs.androidx.paging.common)
    implementation(libs.room.paging)
}