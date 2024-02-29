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

    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.kt)
    implementation(libs.lottie.compose)
}