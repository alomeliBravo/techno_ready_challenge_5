# Script para ejecutar Spring Boot en diferentes ambientes
# Uso: .\run.ps1 [dev|test|prod]

param(
    [Parameter(Position=0)]
    [ValidateSet('dev', 'test', 'prod')]
    [string]$Profile = 'dev'
)

Write-Host "==========================================" -ForegroundColor Cyan
Write-Host "Ejecutando aplicación Spring Boot" -ForegroundColor Cyan
Write-Host "Perfil: $Profile" -ForegroundColor Yellow
Write-Host "==========================================" -ForegroundColor Cyan

# Verificar que Maven esté instalado
$mvnCommand = Get-Command mvn -ErrorAction SilentlyContinue
if (-not $mvnCommand) {
    Write-Host "Error: Maven no está instalado o no está en el PATH" -ForegroundColor Red
    exit 1
}

# Verificar que exista el archivo de propiedades
$propertiesFile = "src\main\resources\application-$Profile.properties"
$ymlFile = "src\main\resources\application-$Profile.yml"

if (-not (Test-Path $propertiesFile) -and -not (Test-Path $ymlFile)) {
    Write-Host "Advertencia: No se encontró $propertiesFile ni $ymlFile" -ForegroundColor Yellow
    $response = Read-Host "¿Desea continuar de todos modos? (s/n)"
    if ($response -notmatch '^[sS]$') {
        exit 1
    }
}

# Ejecutar la aplicación
Write-Host "Iniciando aplicación..." -ForegroundColor Green

# Opción 1: Usar variable de entorno (más confiable en PowerShell)
$env:SPRING_PROFILES_ACTIVE = $Profile
mvn spring-boot:run

# Si la opción anterior no funciona, descomenta una de estas alternativas:

# Opción 2: Ejecutar en CMD desde PowerShell
# cmd /c "mvn spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=$Profile"

# Opción 3: Usar Start-Process
# Start-Process -FilePath "mvn" -ArgumentList "spring-boot:run", "-Dspring-boot.run.arguments=--spring.profiles.active=$Profile" -NoNewWindow -Wait