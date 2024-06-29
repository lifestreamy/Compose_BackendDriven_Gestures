package com.github.build_logic.plugins

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.github.build_logic.configs.configureKotlinAndroid
import com.github.build_logic.defaultLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
                testOptions.animationsDisabled = true
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                beforeVariants {
                    it.androidTest.enable = it.androidTest.enable
                            && target.projectDir.resolve("src/androidTest").exists()
                }
            }
        }
    }
}
