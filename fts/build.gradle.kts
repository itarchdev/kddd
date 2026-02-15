import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.vanniktech)
}

kotlin {
    jvmToolchain(17)

    compilerOptions {
        apiVersion.set(KotlinVersion.KOTLIN_2_3)
    }
    explicitApi()
}

mavenPublishing {
    coordinates("$group", name, "$version")
    // publishToMavenCentral(SonatypeHost.DEFAULT)
    // or when publishing to https://central.sonatype.com/
    publishToMavenCentral(automaticRelease = true)
    signAllPublications()
}

