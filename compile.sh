#!/bin/bash
echo "Compilando:" 
mvn package -Dmaven.test.skip=true
