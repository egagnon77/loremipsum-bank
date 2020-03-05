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

## Things I've done for the BD part :
https://medium.com/codefountain/develop-a-spring-boot-and-mysql-application-and-run-in-docker-end-to-end-15b7cdf3a2ba

#### install Docker

    sudo apt-get update
    sudo apt install docker.io
#### Start and Automate Docker

    sudo systemctl start docker
    sudo systemctl enable docker
    
#### install Docker-compose

    sudo apt install docker-compose
#### I created a file ../resources/docker-compose.yml

#### I launched Docker MySQL
    sudo docker-compose up
    -Next time you could use Docker Dashboard directly
     
#### I installed MySQL Workbench (Visual Tool)

    sudo apt update
    sudo apt install mysql-workbench
#### Create connection In MySQL Workbench
    
    Hostname 127.0.0.1     port: 3308
    username: root
    password: bankpassword (store in Keychain)
    Default Shema: bankBD
    
#### Create Client into table
    INSERT INTO client VALUES (1); COMMIT; 
    
    
#### Start the application
    right mouse button on "com/mgl7460/h20/bank/Application.java" -> Debug

#### Use Postman or Firefox to make a request
    http://localhost:8081/api/bank/v1/client/1