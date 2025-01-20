import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

val apiPropertiesFile = rootProject.file("api.properties")
val appPropertiesFile = rootProject.file("app.properties")
val apiProperties = Properties().apply {
    load(FileInputStream(apiPropertiesFile))
}
val appProperties = Properties().apply {
    load(FileInputStream(appPropertiesFile))
}

android {
    namespace = appProperties.getProperty("APPLICATION_ID")
    compileSdk = appProperties.getProperty("TARGET_SDK").toInt()

    defaultConfig {
        applicationId = appProperties.getProperty("APPLICATION_ID")
        minSdk = appProperties.getProperty("MIN_SDK").toInt()
        targetSdk = appProperties.getProperty("TARGET_SDK").toInt()
        versionCode = appProperties.getProperty("VERSION_CODE").toInt()
        versionName = appProperties.getProperty("VERSION_NAME")
        buildConfigField("String", "ACCESS_TOKEN", apiProperties.getProperty("ACCESS_TOKEN"))
        buildConfigField("String", "BASE_URL", apiProperties.getProperty("BASE_URL"))

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}