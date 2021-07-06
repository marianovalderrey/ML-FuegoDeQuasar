package com.ml.springbootml.services;

import java.util.HashMap;
import java.util.Map;

import com.ml.springbootml.entities.CargoSpacecraft;
import com.ml.springbootml.entities.Position;
import com.ml.springbootml.entities.Satellite;
import com.ml.springbootml.entities.SatelliteContainer;
import com.ml.springbootml.exceptions.MessageException;
import com.ml.springbootml.exceptions.SatelliteException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@Service
public class IntelligenceService {

    @Autowired
    private Environment environment;
	
    @Autowired
    LocationService locationService;
    
    @Autowired
    MessageService messageService;

    public IntelligenceService (){}

    public CargoSpacecraft getCargoSpacecraft(SatelliteContainer satelliteContainer) throws SatelliteException, MessageException {
        if (satelliteContainer.getSatellites().size() != 3){
            throw new SatelliteException("El número de satélites no es correcto, tienen que ser 3.");
        }
        
        this.loadSatellitesPosition(satelliteContainer);
        locationService.getLocation(satelliteContainer);

        Position cargoLocation = this.locationService.getLocation(satelliteContainer);
        String   cargoMessage  = this.messageService.getMessage(satelliteContainer.getMessages());

        CargoSpacecraft cargoSpacecraft = new CargoSpacecraft(cargoLocation, cargoMessage);

        return cargoSpacecraft;
    }
    public void loadSatellitesPosition (SatelliteContainer satelliteContainer) throws SatelliteException{
        int countSat = Integer.parseInt(environment.getProperty("satellites.count"));
        
        Map<String, String> satellitesMap = new HashMap<String, String>();
        for (int i = 0; i < countSat; i++){
            satellitesMap.put(environment.getProperty("satellites."+i+".name"), environment.getProperty("satellites."+i+".position"));
        }
        try {
            for (Satellite satellite: satelliteContainer.getSatellites()) {
                satellite.setPosition(new Position(satellitesMap.get(satellite.getName())));
            }
        } catch (Exception e) {
            throw new SatelliteException("Hay un error con los nombres de los satelites.");
        }
        
    }
}