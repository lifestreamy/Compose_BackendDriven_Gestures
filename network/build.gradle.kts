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
    api(libs.ktor.client.core)

    api(libs.ktor.server.cio)
    api(libs.ktor.client.cio)

    api(libs.ktor.server.websockets)
    api(libs.ktor.client.websockets)

    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutines.android)

    implementation(libs.ktor.serialization.kotlinx.json)

    testImplementation(libs.ktor.serialization.kotlinx.json)

    testImplementation(libs.kotlinx.coroutines.test)

    testImplementation(libs.ktor.client.core)
    testImplementation(libs.ktor.server.core)

    testImplementation(libs.ktor.client.cio)
    testImplementation(libs.ktor.server.cio)

    testImplementation(libs.ktor.client.websockets)
    testImplementation(libs.ktor.server.websockets)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}