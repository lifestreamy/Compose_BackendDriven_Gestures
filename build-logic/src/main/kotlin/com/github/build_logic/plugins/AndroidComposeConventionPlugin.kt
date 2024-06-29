package com.github.build_logic.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.github.build_logic.configs.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            val appExtension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(appExtension)
        }
    }

}