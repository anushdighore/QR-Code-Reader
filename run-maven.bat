@echo off
REM Run the QR Code Reader GUI using Maven
echo Starting QR Code Reader...
echo.

REM Check if mvnd is available, fallback to mvn
where mvnd >nul 2>nul
if %errorlevel% equ 0 (
    mvnd exec:java -Dexec.mainClass="QRCodeAppGUI"
) else (
    mvn exec:java -Dexec.mainClass="QRCodeAppGUI"
)
