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
    api(libs.ktor.server.netty)

    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutines.android)


    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.ktor.client.core)
    testImplementation(libs.ktor.client.cio)
    testImplementation(libs.ktor.server.core)
    testImplementation(libs.ktor.server.netty)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}