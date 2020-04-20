[![pipeline status](https://gitlab.com/eric.c.gagnon/bank/badges/master/pipeline.svg)](https://gitlab.com/eric.c.gagnon/bank/-/commits/master)

# Bank

## Reports
[Rapport MVP](https://gitlab.com/eric.c.gagnon/bank/-/raw/master/report/RapportLivrable1.pdf)

## Prerequisites

### Maven
https://maven.apache.org/install.html

### Java
https://www.oracle.com/java/technologies/javase-jdk8-downloads.html

### Docker and Docker compose
https://docs.docker.com/install/
  
## How to start the Bank Server and DB
```bash
sudo ./start-docker.sh
```
> Bank server will run on: http://localhost:8081/api/bank/v1

## V1 Release commands
### As of release V1 only two commands are implemented:
```bash
# Create a client
./employee --add CLIENT_NAME

# List client products
./client -n CLIENT_NAME --status
```

### Launch employee module from command line
#### On Linux
```bash
# Example
./employee --list Test
```

#### On Windows
Be sure to have Git Bash that will install the command-line shell
You can launch the script employee that is at the root of the project folder
In Windows cmd prompt or powershell you have to specify the .sh extension.

```bash
# Example
employee.sh --list Test
```

## Developper's rules

### How to link commit with GitLab issue
```bash
git commit -m "this is my commit message. Ref #1"
```

## Sonarqube 
[Sonarqube analysis](https://sonarcloud.io/dashboard?id=org.loremipsum%3Amgl7460-h20-bank)

## Generate Jacoco Report
1. Run "mvn clean test jacoco:report"
2. In bank module, under target you will find site/jacoco/index.html
3. Open this file from a browser.
