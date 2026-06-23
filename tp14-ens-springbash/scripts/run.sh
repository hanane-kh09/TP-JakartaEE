#!/bin/bash
mkdir -p logs

echo "Démarrage de l'application Spring Boot..."
nohup mvn spring-boot:run > logs/app.log 2>&1 &
echo "Application démarrée avec succès (PID : $!)"
