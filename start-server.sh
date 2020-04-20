#!/bin/bash

set -ex
mvn spring-boot:run -pl bank -Dspring-boot.run.profiles=dev