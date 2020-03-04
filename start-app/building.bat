@echo off

start start-eureka.bat
timeout /t 25
start start-zuul.bat
timeout /t 25
call start-note.bat

pause