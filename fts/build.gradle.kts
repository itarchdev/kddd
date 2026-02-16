import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.vanniktech)
    alias(libs.plugins.dokka)
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

dokka {
    dokkaPublications.configureEach {
        // В V2 outputDirectory настраивается через публикацию
        outputDirectory.set(rootProject.layout.projectDirectory.dir("docs/fts"))
    }
}
