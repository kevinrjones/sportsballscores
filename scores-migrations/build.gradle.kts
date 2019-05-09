val flyway_version: String by project
val h2_version: String by project
val mysql_connector_version: String by rootProject.project
val mySqlConnectionString: String by project
val mySqlUser: String by project

buildscript {
    val flyway_version: String by project
    val h2_version: String by project
    val mysql_connector_version: String by project

    dependencies {
        classpath("com.h2database:h2:$h2_version")
        classpath("mysql:mysql-connector-java:$mysql_connector_version")
        classpath("org.flywaydb:flyway-gradle-plugin:$flyway_version")
    }
}

plugins {
    id("org.flywaydb.flyway") version "5.2.4"
}

flyway {
    schemas = arrayOf("sportball")
    locations = arrayOf(
        "filesystem:$projectDir/migrations/common",
        "filesystem:$projectDir/migrations/mysql"
    )
    sqlMigrationPrefix = ""
    baselineOnMigrate = true
    outOfOrder = true
}

val sportsMysqlPassword: String? by project

if(sportsMysqlPassword == null) {
    logger.error("sportsMysqlPassword not set. Please add a USER_HOME/.gradle/gradle.properties and add a jacket_password value")
} else {
    println("MySql password found")
}

// extending flyway tasks
tasks.register<org.flywaydb.gradle.task.FlywayMigrateTask>("migrateDatabaseMySql") {
    url = mySqlConnectionString
    user = mySqlUser
    password = sportsMysqlPassword
}

tasks.register<org.flywaydb.gradle.task.FlywayMigrateTask>("migrateDatabaseInMemory") {
    url = "jdbc:h2:mem:mydb2"
    user = "myUsr2"
    password = sportsMysqlPassword
}


//Properties props = new Properties()
//InputStream ins = new FileInputStream("/path/file.properties")
//props.load(ins)
//ins.close()
//

