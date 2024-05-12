// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.3"
}
detekt {
    source = files(projectDir)
    parallel = true
    config = files("${project.rootDir}/config/detekt.yml")
}
