# vbim2101 - IDDE labor git repo 2022

## How to run:

The first step is not absolutely necessary.
Do it just in case you want to test the last part of the homework.

1. Set up two profiles in Run/Debug configurations (Gradle -> desktop:run) with the following environment variable: daoType=mem/jdbc. (Choose only one / configuration)
2. Choose the configuration with which you want to run your program, or just simply run it and it will read the daoType from the application.yaml
3. You can use gradle run in console if you would like
4. You can use gradle deploy to deploy it to Tomcat automatically if the CATALINA_HOME environment variable is set
5. As the assignment did not require I did not update the website with the second entity, only the desktop gradle project takes it into account 