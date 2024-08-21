plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-kapt") //.ds
    id ("androidx.navigation.safeargs.kotlin") //.ds
}

android {
    namespace = "com.example.youtubeapi"
    compileSdk = 34
    buildFeatures {
        viewBinding = true
    }
    defaultConfig {
        applicationId = "com.example.youtubeapi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.gridlayout)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.exoplayer)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Kotlin .ds
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // picasso
    implementation("com.squareup.picasso:picasso:2.8")

    //Corotuine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

        implementation("com.pierfrancescosoffritti.androidyoutubeplayer:chromecast-sender:0.28")
        implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")

}