val kotlin_version: String by rootProject.project
val logback_version: String by rootProject.project
val exposed_version: String by project
val hikari_version: String by project

plugins {
    application
}

group = "sportscores"
version = "0.0.1"

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {
    implementation("org.jetbrains.exposed:exposed:$exposed_version")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("com.zaxxer:HikariCP:$hikari_version")
}
