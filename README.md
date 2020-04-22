[![GitlabCI Build Status](https://gitlab.com/eric.c.gagnon/bank/badges/master/pipeline.svg)](https://gitlab.com/eric.c.gagnon/bank/-/commits/master)
[![TravisCI Build Status](https://travis-ci.com/egagnon77/loremipsum-bank.svg?branch=master)](https://travis-ci.com/egagnon77/loremipsum-bank)

# Bank

## Reports
[Rapport MVP](https://gitlab.com/eric.c.gagnon/bank/-/raw/master/report/RapportLivrable1.pdf)

## Mirror Repository
[Github](https://github.com/egagnon77/loremipsum-bank)

## CI/CD
* [Gitlab-CI](https://gitlab.com/eric.c.gagnon/bank/pipelines)
* [Travis-CI](https://travis-ci.com/github/egagnon77/loremipsum-bank)

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
* [Sonarqube analysis (From Gitlab-CI)](https://sonarcloud.io/dashboard?id=org.loremipsum%3Amgl7460-h20-bank)
* [Sonarqube analysis (From Travis-CI)](https://sonarcloud.io/dashboard?id=loremipsum-bank)