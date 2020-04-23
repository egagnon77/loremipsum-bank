[![GitlabCI Build Status](https://gitlab.com/eric.c.gagnon/bank/badges/master/pipeline.svg)](https://gitlab.com/eric.c.gagnon/bank/-/commits/master)
[![TravisCI Build Status](https://travis-ci.com/egagnon77/loremipsum-bank.svg?branch=master)](https://travis-ci.com/egagnon77/loremipsum-bank)

# LoremIpsum Bank

## Reports
* [Rapport MVP](https://gitlab.com/eric.c.gagnon/bank/-/raw/master/report/RapportLivrable1.pdf)
* [Rapport Final](https://gitlab.com/eric.c.gagnon/bank/-/raw/master/report/RapportLivrableFinal.pdf)

## Mirror Repository
[LoremIpsum Bank on Github](https://github.com/egagnon77/loremipsum-bank)

## CI/CD
* [Gitlab-CI](https://gitlab.com/eric.c.gagnon/bank/pipelines)
* [Travis-CI](https://travis-ci.com/github/egagnon77/loremipsum-bank)

# Docker Hub
[Docker Images](https://hub.docker.com/r/loremipsumbank/server/tags)
> * All **tags** and **latest** are related to Gitlab CI deployment.
> * Tag **travis-latest** is related to Travis CI deployment.

## Sonarqube Analysis
* [Sonarqube analysis (From Gitlab-CI)](https://sonarcloud.io/dashboard?id=org.loremipsum%3Amgl7460-h20-bank)
* [Sonarqube analysis (From Travis-CI)](https://sonarcloud.io/dashboard?id=loremipsum-bank)

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

## Build Client and Employee 
```bash
sudo mvn clean install
```
> Use ```./client.sh``` and ```./employee.sh``` to launch command. The ```.sh``` is important to avoid conflict with the folders ./client and ./employee. 

## Developper's rules

### How to link commit with GitLab issue
```bash
git commit -m "this is my commit message. Ref #1"
```