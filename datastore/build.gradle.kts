plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.hilt)
}

android {
    namespace = "com.github.datastore"
}

dependencies {
    implementation(libs.androidx.dataStore.preferences)
    implementation(libs.androidx.dataStore.core)
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.junit)

}