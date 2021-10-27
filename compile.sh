#!/bin/bash
echo "Compilando:" 
mvn package -Dmaven.test.skip=true
cd target/
echo "Upando para web:"
curl --upload-file projeto_vaca_ultimo-0.0.1-SNAPSHOT.jar https://transfer.sh/vaca.jar

