#!/bin/bash
echo "Archivage des logs..."
tar -czf logs_$(date +%Y%m%d).tar.gz logs/
echo "Logs archivés dans logs_$(date +%Y%m%d).tar.gz"
