package com.ml.springbootml.services;


import java.util.List;

import com.ml.springbootml.entities.Position;
import com.ml.springbootml.entities.Satellite;
import com.ml.springbootml.entities.SatelliteContainer;
import com.ml.springbootml.exceptions.SatelliteException;

import org.springframework.stereotype.Service;

@Service
public class LocationService {

    public Position  getLocation(SatelliteContainer satelliteContainer) throws SatelliteException{
        if (satelliteContainer.getSatellites().size() != 3){
            throw new SatelliteException("La cantidad de satélites tiene que ser de 3");
        }
        List<Satellite> satellites = satelliteContainer.getSatellites();

        float functionA = (2*satellites.get(0).getPosition().getX()) - (2*satellites.get(1).getPosition().getX());
        float functionB = (2*satellites.get(0).getPosition().getY()) - (2*satellites.get(1).getPosition().getY());
        float functionC = (float) (Math.pow(satellites.get(1).getDistance(), 2) - Math.pow(satellites.get(0).getDistance(), 2)
                           - Math.pow(satellites.get(1).getPosition().getX(), 2) + Math.pow(satellites.get(0).getPosition().getX(), 2)
                           - Math.pow(satellites.get(1).getPosition().getY(), 2) + Math.pow(satellites.get(0).getPosition().getY(), 2));
        float functionD = (2*satellites.get(1).getPosition().getX()) - (2*satellites.get(2).getPosition().getX());
        float functionE = (2*satellites.get(1).getPosition().getY()) - (2*satellites.get(2).getPosition().getY());
        float functionF = (float) (Math.pow(satellites.get(2).getDistance(), 2) - Math.pow(satellites.get(1).getDistance(), 2)
                           - Math.pow(satellites.get(2).getPosition().getX(), 2) + Math.pow(satellites.get(1).getPosition().getX(), 2)
                           - Math.pow(satellites.get(2).getPosition().getY(), 2) + Math.pow(satellites.get(1).getPosition().getY(), 2));
        
        float x = ((functionC*functionE)-(functionF*functionB))/((functionE*functionA)-(functionD*functionB));
        float y = ((functionD*functionC)-(functionA*functionF))/((functionD*functionB)-(functionA*functionE));

        return new Position(x, y);
    }
}