#!/bin/bash

# Script para ejecutar Spring Boot en diferentes ambientes
# Uso: ./run.sh [dev|test|prod]

PROFILE=${1:-dev}

echo "=========================================="
echo "Ejecutando aplicación Spring Boot"
echo "Perfil: $PROFILE"
echo "=========================================="

# Verificar que Maven esté instalado
if ! command -v mvn &> /dev/null
then
    echo "Error: Maven no está instalado o no está en el PATH"
    exit 1
fi

# Verificar que el perfil sea válido
if [[ ! "$PROFILE" =~ ^(dev|test|prod)$ ]]; then
    echo "Error: Perfil inválido. Use: dev, test o prod"
    echo "Uso: ./run.sh [dev|test|prod]"
    exit 1
fi

# Verificar que exista el archivo de propiedades
PROPERTIES_FILE="src/main/resources/application-${PROFILE}.properties"
YML_FILE="src/main/resources/application-${PROFILE}.yml"

if [[ ! -f "$PROPERTIES_FILE" && ! -f "$YML_FILE" ]]; then
    echo "Advertencia: No se encontró $PROPERTIES_FILE ni $YML_FILE"
    echo "¿Desea continuar de todos modos? (s/n)"
    read -r response
    if [[ ! "$response" =~ ^[sS]$ ]]; then
        exit 1
    fi
fi

# Ejecutar la aplicación
echo "Iniciando aplicación..."
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=$PROFILE"