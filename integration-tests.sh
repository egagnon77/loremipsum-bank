#!/bin/bash

./employee.sh --add CLIENT_NAME
./employee.sh --list CLIENT_NAME
./employee.sh --upgrade CLIENT_NAME
./client.sh -n CLIENT_NAME --subscribe 1
./client.sh -n CLIENT_NAME --unsubscribe 1
./client.sh -n CLIENT_NAME --subscribe 2
./employee.sh --accept 2 --client CLIENT_NAME
./client.sh -n CLIENT_NAME --unsubscribe 2
./employee.sh --accept 2 --client CLIENT_NAME
./client.sh -n CLIENT_NAME --subscribe 2
./employee.sh --tasks
./employee.sh --reject 2 --client CLIENT_NAME
./employee.sh --downgrade CLIENT_NAME
./client.sh -n CLIENT_NAME --status
./client.sh -n CLIENT_NAME --avail


