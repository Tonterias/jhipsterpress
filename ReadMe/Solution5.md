# Problem 5: Change H2 Database from In-memory to Disk

Jon solved this problem and it is explained here: https://stackoverflow.com/questions/50833979/how-to-reset-h2-database-to-the-original-state-after-jhipster-jdl-generation/50837891#50837891

First, go to your File Explorer and change the properties of your databaseName as you need and comment the other option:

src/main/resources/.h2.server.properties

		@@ -1,5 +1,5 @@
		 #H2 Server Properties
		-0=JHipster H2 (Disk)|org.h2.Driver|jdbc\:h2\:file\:./target/h2db/db/databaseName|databaseName
		+0=JHipster H2 (Memory)|org.h2.Driver|jdbc\:h2\:mem\:databaseName|databaseName
		 webAllowOthers=true
		 webPort=8082
		 webSSL=false
View
 
2  src/main/resources/config/application-dev.yml

	@@ -32,7 +32,7 @@ spring:
		         serialization.indent_output: true
		     datasource:
		         type: com.zaxxer.hikari.HikariDataSource
		-        url: jdbc:h2:file:./target/h2db/db/h2switch;DB_CLOSE_DELAY=-1
		+        url: jdbc:h2:mem:databaseName;DB_CLOSE_DELAY=-1
		         username: databaseName
		         password:
		     h2:

		     
