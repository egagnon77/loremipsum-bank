#!/bin/bash

./employee.sh --add CLIENT_NAME
./client.sh -n CLIENT_NAME --status
./client.sh -n CLIENT_NAME --avail
./employee.sh --upgrade CLIENT_NAME
./employee.sh --downgrade CLIENT_NAME
./employee.sh --tasks