#!/bin/bash
echo "Arrêt de l'application Spring Boot..."
PID=$(ps aux | grep 'spring-boot:run' | grep -v grep | awk '{print $2}')

if [ -z "$PID" ]; then
  echo "Aucun processus Spring Boot trouvé."
else
  kill -9 $PID
  echo "Processus $PID arrêté avec succès."
fi
