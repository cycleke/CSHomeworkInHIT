@echo off
set /p start=��ѡ�� (1��2��3) �󰴻س���:

2>nul call :case_%start% #jump to case
if errorlevel 1 call :case_default

echo done.
exit /b

:case_1
  java -jar jar/P1.jar
  goto end_case
:case_2
  java -jar jar/P2.jar
  goto end_case
:case_3
  java -jar jar/P3.jar
  goto end_case
:case_default
  echo "��û������ 1 �� 3 ֮�������"
  pause
  goto end_case
:end_case
  ver > nul
  goto :eof
