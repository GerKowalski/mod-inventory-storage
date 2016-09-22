#!/usr/bin/env bash

kbport=${1:-9401}
catalogueport=${2:-9402}

./create-tenant.sh

./register.sh ${kbport} ${catalogueport}

./start-with-sample-data.sh ${kbport} ${catalogueport}

cd demo/ui

npm install

./node_modules/.bin/webpack-dev-server --host 0.0.0.0



