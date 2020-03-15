[![pipeline status](https://gitlab.com/eric.c.gagnon/bank/badges/master/pipeline.svg)](https://gitlab.com/eric.c.gagnon/bank/-/commits/master)

# Bank

## Prerequisites

### Maven
https://maven.apache.org/install.html

### Java
https://www.oracle.com/java/technologies/javase-jdk8-downloads.html

### Docker and Docker compose
https://docs.docker.com/install/
  
## How to start the DB and Bank Server
```bash
# Start the DB server (docker-compose)
./start-db.sh

# When the db is started
# Start the Bank server
./start-server.sh
```
Bank server will run on : http://localhost:8081/api/bank/v1

## How to link commit with GitLab issue

`git commit -m "this is my commit message. Ref #1"`

## Sonarqube 
[Sonarqube analysis](https://sonarcloud.io/dashboard?id=org.loremipsum%3Amgl7460-h20-bank)

## Generate Jacoco Report : 
1- Run "mvn clean test jacoco:report"
2- In bank module, under target you will find site/jacoco/index.html
3- Open this file from a browser...

#### Create connection In MySQL
    Hostname 127.0.0.1     port: 3308
    username: root
    password: bankpassword (store in Keychain)
    Default Shema: bankBD


#### Launch employee module from command line
##### On Windows
    Be sure to have Git Bash that will install the command-line shell
    You can launch the script "employee.sh" that is at the root of the project folder
    In Windows cmd prompt or powershell you have to specify the .sh extension.
    Ex: employee.sh --list Test
    In Linux or in Git Bash just type
   
        
    Will eventually list products of the "Test" custumer

