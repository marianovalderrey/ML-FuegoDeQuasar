package com.ml.springbootml.controllers;

/**
 * Para correr la aplicación hay que ejecutar:
 * ./mvnw package && java -jar target/spring-boot-ml-0.1.0.jar
 * Viamonte 2449 10 a 18. 
 * El nombre del jar y la versión tienen que ser los que aparecen en el archivo pom.xml
 * <version>0.1.0</version>
 * <name>spring-boot-ml</name>
	
 */

import com.ml.springbootml.entities.Satellite;
import com.ml.springbootml.entities.SatelliteContainer;
import com.ml.springbootml.exceptions.MessageException;
import com.ml.springbootml.exceptions.SatelliteException;
import com.ml.springbootml.services.IntelligenceService;
import com.ml.springbootml.services.SessionService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MLApiController {
	
	@Autowired
    IntelligenceService intelligenceService;

	@Autowired
    SessionService sessionService;
	
	@PostMapping("/topsecret")
	public ResponseEntity topsecret(@RequestBody SatelliteContainer satellites) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(intelligenceService.getCargoSpacecraft(satellites));
		} catch (MessageException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (SatelliteException e) { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}
		
	}

	@RequestMapping(value = "/topsecret_split/{name}", method = { RequestMethod.POST,  RequestMethod.GET })
	public ResponseEntity topsecret_split(@RequestBody Satellite satellite,@PathVariable String name, HttpServletRequest request) {
		satellite.setName(name);
		sessionService.setSatellite(satellite, request.getSession());
		if (sessionService.informationComplete(request.getSession())){
			try {
				/**
				 * Limpio la session como para que se pueda hacer una ejecución nueva con parámetros nuevos.
				 */
				return ResponseEntity.status(HttpStatus.OK).body(intelligenceService.getCargoSpacecraft(sessionService.getSatelliteContainer(request.getSession())));
			} catch (MessageException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			} catch (SatelliteException e) { 
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			} catch (Exception e){
				throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
			}
			
		}
		else {
			HashMap<String, String> entity = new HashMap<String, String>();
            entity.put("message", "En este momento hay información de " + sessionService.getSatellitesSize(request.getSession()) + " satélites");
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		}
		//SatelliteContainer = satellites = new SatelliteContainer();

	}
    
}
