#!/bin/bash
javac -cp junit-platform-console-standalone-1.11.0.jar -d $1  ./src/main/java/ru/nsu/stolyarov/Heapsort.java ./src/test/java/ru/nsu/stolyarov/MainTest.java
if [ $? -gt 0 ]; then
    echo "Ошибка компиляции исходников"
    exit 1
fi
jar -c -f $1/ru/nsu/stolyarov/Heapsort.jar $1/ru/nsu/stolyarov/Heapsort.class
if [ $? -gt 0 ]; then
    echo "Ошибка сборки jar"
    exit 2
fi
java -javaagent:./jacoco-0.8.12/lib/jacocoagent.jar=destfile=$1/jacoco/jacoco.exec -jar ./junit-platform-console-standalone-1.11.0.jar -cp $1 --scan-classpath
if [ $? -gt 0 ]; then
    echo "Ошибка тестирования"
    exit 3
fi
javadoc -d $1/doc ./src/main/java/ru/nsu/stolyarov/Heapsort.java
if [ $? -gt 0 ]; then
    echo "Ошибка создания документации"
    exit 4
fi
java -jar ./jacoco-0.8.12/lib/jacococli.jar report $1/jacoco/jacoco.exec --classfiles $1/ru/nsu/stolyarov/Heapsort.class --classfiles $1/ru/nsu/stolyarov/MainTest.class  --html $1/jacoco  --sourcefiles ./src/main/java/ru/nsu/stolyarov/Heapsort --sourcefiles ./src/test/java/ru/nsu/stolyarov/MainTest
if [[ $? -gt 0 ]]; then
    echo "ошибка генерации отчета о покрытии кода тестами"
    exit 5
fi

echo "Сборка успешно завершена"
