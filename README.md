# Cron Parser

Cron Parser is java spring boot console application. It takes cron string expression as single argument and generates human readable format as ouput. e.g

Input arguments : "*/15 0 1,15 * 1-5 /usr/bin/find" which is same as "minute hour day-of-month month day-of-week unixCommand"

Output result as below :

minute         0 15 30 45
hour           0
day of month   1 15
month          1 2 3 4 5 6 7 8 9 10 11 12
day of week    1 2 3 4 5
command        /usr/bin/find


# Setup and Build

Prerequisite :  Java 8 , Gradle

<li>Execute below command from within the folder. It will download and setup the libraries which are used in this application.<br/><br/></li>


``` java
gradle build
```

# Usage

<li>Run below command along with cron expression to convert into Human readable format.<br/><br/></li>

``` java
# "*/15 0 1,15 * 1-5 /usr/bin/find" cron string passed as single argument to application.

 java -jar .\build\libs\cronExpressionParser-0.0.1-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```






