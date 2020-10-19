# JavaagentSimpleProject

**Для запуска необходимо:**
  1. Java не ниже 8
  2. Maven

**Использование**

Запустить .bat файл с параметром:

    1, если необходимо собрать модуль javaagent-module в .jar архив и обновить уже существующий архив для запуска
  
    0, если необходимо запустить уже существующий .jar архив с javaagent (архив лежит в репозитории по пути src/main/java/javaagent-module-1.0-SNAPSHOT-jar-with-dependencies.jar)
  
  ```./start_javaagent.bat [1|0]```
