plugins {
    id("mobilechallenge.android.library")
    id("mobilechallenge.android.hilt")
    id("mobilechallenge.android.room")
}

android {
    namespace = "com.manuelblanco.mobilechallenge.core.database"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.common)
    implementation(libs.room.paging)

    androidTestImplementation(project(":core:testing"))
}