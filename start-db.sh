#!/bin/bash

set -ex

pushd ./bank/src/main/resources
    docker-compose up &
popd