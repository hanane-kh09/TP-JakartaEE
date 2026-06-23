#!/bin/bash
SERVER_USER="user"
SERVER_HOST="serveur"
REMOTE_PATH="/opt/apps/"

echo "Transfert du JAR vers le serveur distant..."
scp target/*.jar $SERVER_USER@$SERVER_HOST:$REMOTE_PATH
echo "Transfert terminé."
