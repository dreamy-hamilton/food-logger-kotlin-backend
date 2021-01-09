import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.spring") version "1.4.30-M1"
    id("org.springframework.boot") version "2.2.7.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

group = "me.dreamy-hamilton"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    // SPRING
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // EXPOSED
    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.28.1")

    // OKHTTP
    implementation("com.squareup.okhttp3:okhttp:4.9.0")

    // JACKSON
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.+")

    // FLYWAY
    implementation("org.flywaydb:flyway-core")

    // TEST
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    testImplementation("com.h2database:h2:1.4.199")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "12"
}