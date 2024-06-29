package com.github.build_logic

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.defaultLibs: VersionCatalog
    get() = extensions.getByType(VersionCatalogsExtension::class).named("libs")