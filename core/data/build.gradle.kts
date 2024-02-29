plugins {
    alias(libs.plugins.mobilechallenge.android.library)
    alias(libs.plugins.mobilechallenge.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.data"
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.database)
    implementation(projects.core.datastore)
    implementation(projects.core.network)

    api(libs.androidx.paging.runtime)
    api(libs.androidx.paging.common)
    api(libs.androidx.paging.compose)

    implementation(libs.retrofit.core)
}