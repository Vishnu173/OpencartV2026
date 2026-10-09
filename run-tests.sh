#!/bin/bash

cd "$(dirname "$0")" || exit 1

echo "================================="
echo "Starting Maven Test Execution"
echo "================================="

mvn clean test

if [ $? -eq 0 ]; then
    echo "================================="
    echo "Maven execution completed"
    echo "================================="
else
    echo "================================="
    echo "Maven execution failed"
    echo "================================="
    exit 1
fi