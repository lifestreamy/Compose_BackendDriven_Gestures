plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.github.network"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(libs.kotlinx.serialization.json)
    api(libs.kotlinx.datetime)

    api(libs.ktor.server.core)
    api(libs.ktor)

    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutines.android)

    testImplementation(libs.junit)
}