#!/bin/bash
echo $(sed -i "s/REST_API_HOST/${REST_API_HOST}/g" proxy.conf.json)
echo $(exec "$@")
npm start