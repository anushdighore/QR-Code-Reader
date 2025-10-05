@echo off
REM Build the QR Code Reader project using Maven Daemon (mvnd)
echo Building QR Code Reader with Maven...
echo.

REM Check if mvnd is available
where mvnd >nul 2>nul
if %errorlevel% equ 0 (
    echo Using Maven Daemon ^(mvnd^)...
    mvnd clean package
    goto :check_result
)

REM Check if mvn is available
where mvn >nul 2>nul
if %errorlevel% equ 0 (
    echo Using Maven ^(mvn^)...
    mvn clean package
    goto :check_result
)

REM Neither found
echo.
echo ERROR: Maven is not installed or not in PATH!
echo.
echo Please install Maven from one of these options:
echo   1. Maven Daemon ^(recommended^): https://github.com/apache/maven-mvnd
echo   2. Apache Maven: https://maven.apache.org/download.cgi
echo.
echo Or install via Chocolatey:
echo   choco install maven
echo   choco install maven-mvnd
echo.
pause
exit /b 1

:check_result
if %errorlevel% equ 0 (
    echo.
    echo ========================================
    echo Build successful!
    echo Executable JAR: target\qr-code-reader.jar
    echo ========================================
    echo.
    echo To run the application:
    echo   java -jar target\qr-code-reader.jar
    echo   or
    echo   run-jar.bat
) else (
    echo.
    echo Build failed! Check the error messages above.
    exit /b 1
)
