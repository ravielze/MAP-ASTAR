call mvn clean install >> build.log
cd target && java -jar mapastar-1.0-SNAPSHOT.jar
cd ..