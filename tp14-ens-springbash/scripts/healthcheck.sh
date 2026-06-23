#!/bin/bash
echo "Vérification de l'état de l'application..."
curl -s http://localhost:8085/actuator/health
echo ""
