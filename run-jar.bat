@echo off
REM Run the packaged JAR file
echo Starting QR Code Reader from JAR...
echo.

if not exist "target\qr-code-reader.jar" (
    echo JAR file not found! Please build the project first using build.bat
    echo.
    pause
    exit /b 1
)

java -jar target\qr-code-reader.jar
