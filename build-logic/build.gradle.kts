import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.github.gestures.buildlogic"

repositories {
    google()
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}


gradlePlugin {

    plugins {
        val pluginsPackage = "com.github.build_logic.plugins."
        register("androidApplication") {
            id = "convention.android.application"
            implementationClass = pluginsPackage + "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "convention.android.library"
            implementationClass = pluginsPackage + "AndroidLibraryConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "convention.android.compose"
            implementationClass = pluginsPackage + "AndroidComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "convention.android.library.compose"
            implementationClass = pluginsPackage + "AndroidLibraryComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "convention.android.hilt"
            implementationClass = pluginsPackage + "HiltConventionPlugin"
        }
    }
}