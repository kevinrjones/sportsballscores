val kotlin_version: String by rootProject.project
val logback_version: String by rootProject.project
val mysql_connector_version: String by rootProject.project
val hibernate_jpa_version: String by project
val commons_logging_version: String by project
val spring_boot_version: String by rootProject.project
val hikari_version: String by project

plugins {
    application
    id("org.springframework.boot")
    id ("org.jetbrains.kotlin.plugin.spring") version "1.3.31"
    id ("org.jetbrains.kotlin.plugin.jpa") version "1.3.31"
}

group = "sportscores"
version = "0.0.1"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter:$spring_boot_version")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$spring_boot_version")
    implementation("org.hibernate.javax.persistence:hibernate-jpa-2.1-api:$hibernate_jpa_version")
    implementation("mysql:mysql-connector-java:$mysql_connector_version")
    implementation("com.zaxxer:HikariCP:$hikari_version")

    // todo: do I need these next two?
    implementation("javax.inject:javax.inject:1")
    implementation("commons-logging:commons-logging:$commons_logging_version")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$spring_boot_version")  {
        exclude(module= "junit")
    }
    
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}
