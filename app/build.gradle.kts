plugins {
    id("jacoco")
    alias(libs.plugins.mobilechallenge.android.application)
    alias(libs.plugins.mobilechallenge.android.application.compose)
    alias(libs.plugins.mobilechallenge.android.hilt)
    alias(libs.plugins.mobilechallenge.android.application.jacoco)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.manuelblanco.mobilechallenge"

    defaultConfig {
        applicationId = "com.manuelblanco.mobilechallenge"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "com.manuelblanco.mobilechallenge.core.testing.TicketsTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    implementation(projects.feature.events)
    implementation(projects.feature.venues)
    implementation(projects.feature.favorites)

    implementation(projects.core.designsystem)
    implementation(projects.core.data)
    implementation(projects.core.ui)
    implementation(projects.core.common)
    implementation(projects.core.testing)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.compose.bom)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.kt)
    implementation(libs.lottie.compose)
    implementation(libs.kotlinx.coroutines.test)

    kaptAndroidTest(libs.hilt.compiler)
    kapt(libs.hilt.compiler)

    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.accompanist.testharness)
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(projects.core.testing)

    debugImplementation(libs.androidx.compose.ui.testManifest)
}