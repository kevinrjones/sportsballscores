# Sports Ball Scores

Simple Game to Guess Football Results. This could be association football (soccer), rugby football or American football

## Database Setup

There is a Gradle project called `scores-migrations`, this has two tasks, `migrateDatabaseMySql` and `migrateDatabaseInMemory`, (currently the 'in memory' task has not been set up). The MySql task relies on a database called sportball with the URL `jdbc:mysql://localhost/sportball`, a user called `sportball` and whatever password you want to set. The password is stored in the `USER_HOME/.gradle/gradle.properties` which should look like this:

``` properties
sportsMysqlPassword=mypassword
```


