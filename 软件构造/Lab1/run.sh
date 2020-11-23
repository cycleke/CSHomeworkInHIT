#!/usr/bin//bash

echo "运行哪个程序(1/2/3)"
read p
case $p in
    1) echo "运行P1.jar"
       java -jar jar/P1.jar
       ;;
    2) echo "运行P2.jar"
       java -jar jar/P2.jar
       ;;
    3) echo "运行P3.jar"
       java -jar jar/P3.jar
       ;;
    *) echo '你没有输入 1 到 3 之间的数字'
       ;;
esac
