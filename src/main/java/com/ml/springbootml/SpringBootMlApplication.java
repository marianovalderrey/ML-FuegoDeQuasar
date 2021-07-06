package com.ml.springbootml;

/**
 * Para correr la aplicación hay que ejecutar:
 * ./mvnw package && java -jar target/spring-boot-ml-0.1.0.jar
 * Viamonte 2449 10 a 18. 
 * El nombre del jar y la versión tienen que ser los que aparecen en el archivo pom.xml
 * <version>0.1.0</version>
 * <name>spring-boot-ml</name>
	
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMlApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMlApplication.class, args);
	}

}
