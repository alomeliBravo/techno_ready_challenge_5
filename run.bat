@echo off
REM Script para ejecutar Spring Boot en diferentes ambientes
REM Uso: run.bat [dev|test|prod]

setlocal

REM Establecer perfil por defecto si no se proporciona
if "%1"=="" (
    set PROFILE=dev
) else (
    set PROFILE=%1
)

echo ==========================================
echo Ejecutando aplicacion Spring Boot
echo Perfil: %PROFILE%
echo ==========================================

REM Verificar que Maven este instalado
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo Error: Maven no esta instalado o no esta en el PATH
    exit /b 1
)

REM Verificar que el perfil sea valido
if not "%PROFILE%"=="dev" if not "%PROFILE%"=="test" if not "%PROFILE%"=="prod" (
    echo Error: Perfil invalido. Use: dev, test o prod
    echo Uso: run.bat [dev^|test^|prod]
    exit /b 1
)

REM Verificar que exista el archivo de propiedades
set PROPERTIES_FILE=src\main\resources\application-%PROFILE%.properties
set YML_FILE=src\main\resources\application-%PROFILE%.yml

if not exist "%PROPERTIES_FILE%" if not exist "%YML_FILE%" (
    echo Advertencia: No se encontro %PROPERTIES_FILE% ni %YML_FILE%
    set /p response="Desea continuar de todos modos? (s/n): "
    if /i not "%response%"=="s" (
        exit /b 1
    )
)

REM Ejecutar la aplicacion
echo Iniciando aplicacion...
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=%PROFILE%"

endlocal