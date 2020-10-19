@rem start_javaagent
 
@echo off

IF %1%==1 (
	cd .\javaagent-module\
	
	mvn clean package
	
	cd ../
	
	copy .\javaagent-module\target\javaagent-module-1.0-SNAPSHOT-jar-with-dependencies.jar .\program_module\src\main\java\
)

cd program_module\src\main\java

java -javaagent:javaagent-module-1.0-SNAPSHOT-jar-with-dependencies.jar javaagent.TransactionProcessor

pause