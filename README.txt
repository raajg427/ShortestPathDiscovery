This folder contains files in following structure which are bundled in a directory called Raju_20200513
- data : Contains data files to load predefined data
- src  : Source files of the application
- application.properties : Properties file of the application
- pom.xml : Pom to generate the application
- README : Contains steps to run

Important Parameters :
=>init.data.from.file=true
- Everytime application loads, the data will be read from the file by clearing previously existing data in the db. 
Any changes made on data will be gone when the application restarts.
=>init.data.from.file=false
- The data will be loaded from db, which can have any changes made in previous run. 


Steps to run:(Assumes machine contains Java 8+ and Maven 3.6.*)
After extracting the folder, run the below commands by opening a terminal/command prompt inside the directory(Raju_20200513)

To generates the required jar files to run the application
> mvn package 

To run the application
> java -jar target\raju-0.0.1-SNAPSHOT.jar 
Note: Please point JAVA_HOME to JDK folder (instead of JRE folder)


After successful starting of server, please open http://localhost:8081/ to see the application.



