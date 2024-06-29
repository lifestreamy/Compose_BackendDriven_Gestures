package com.github.build_logic.plugins

import com.github.build_logic.defaultLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }

            dependencies {
                "implementation"(defaultLibs.findLibrary("hilt.android").get())
                "ksp"(defaultLibs.findLibrary("hilt.compiler").get())
            }

        }
    }

}
