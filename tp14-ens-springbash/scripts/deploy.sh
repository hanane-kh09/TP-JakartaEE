#!/bin/bash
mkdir -p logs

echo "Compilation du projet Spring Boot..."
mvn clean package -DskipTests

echo "Déploiement de la nouvelle version..."
java -jar target/*.jar > logs/deploy.log 2>&1 &
echo "Nouvelle version déployée avec succès (PID : $!)"
