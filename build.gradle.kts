import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    application
}

group = "net.kodein.workshop"
version = "1.0"

application.mainClassName = "net.kodein.workshop.server.MainKt"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.5")
    implementation("com.fasterxml.jackson.core:jackson-core:2.9.5")

    val ktorVer = "1.2.4"
    implementation("io.ktor:ktor-server-netty:$ktorVer")
    implementation("io.ktor:ktor-jackson:$ktorVer") {
        exclude("com.fasterxml.jackson.module")
        exclude("com.fasterxml.jackson.core")
    }

    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.dizitart:potassium-nitrite:3.2.0")

    val kodeinVer = "6.4.1"
    implementation("org.kodein.di:kodein-di-generic-jvm:$kodeinVer")
    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:$kodeinVer")
    implementation("org.kodein.di:kodein-di-framework-ktor-server-controller-jvm:$kodeinVer")

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}