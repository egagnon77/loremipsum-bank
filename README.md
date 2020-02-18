# Bank

## How to link commit with GitLab issue

`git commit -m "this is my commit message. Ref #1"`

## Sonarqube 
[Sonarqube analysis](https://sonarcloud.io/dashboard?id=org.loremipsum%3Amgl7460-h20-bank)

## Generate Jacoco Report : 
1- Run "mvn clean test jacoco:report"
2- In bank module, under target you will find site/jacoco/index.html
3- Open this file from a browser...

## Run bank server : 
On Application class with IntelliJ you can simply right click and "Run Application"
Bank server will run on : http://localhost:8081/api/bank/

## Test Request :
/GET http://localhost:8081/api/bank/v1/client/test123
Will return : {"clientId": "test123"}