plugins {
    id("mpp-common")
}

extra["mavenName"] = "MockK common"
extra["mavenDescription"] = "Common(JS and Java) MockK module"

apply(from = "${rootProject.extensions.extraProperties["gradles"]}/additional-archives.gradle")
apply(from = "${rootProject.extensions.extraProperties["gradles"]}/upload.gradle")

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val buildScanApi = rootProject.extensions.getByType<com.gradle.scan.plugin.BuildScanExtension>()

tasks.named<org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon>("compileTestKotlinCommon") {
    doFirst {
        filteredArgumentsMap.forEach { key, value ->
            // attach custom values to the build scan
            buildScanApi.value("$name#filteredArgumentsMap#$key", value)
        }
    }
}

dependencies {
    api(project(":mockk-dsl"))
}
