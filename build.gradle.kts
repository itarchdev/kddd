import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import groovy.json.JsonSlurper

plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.versions)
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
// https://github.com/ben-manes/gradle-versions-plugin
tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

fun getModuleVersion(moduleName: String): String {
    val manifestFile = file(".release-please-manifest.json")
    return if (manifestFile.exists()) {
        val json = JsonSlurper().parse(manifestFile) as Map<String, String>
        json[moduleName] ?: "0.0.1"
    } else "0.0.1"
}

subprojects {
    if (name == "fts" || name == "ksp-processor")
        version = getModuleVersion(name)
}
