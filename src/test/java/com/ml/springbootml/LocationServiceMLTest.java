package com.ml.springbootml;

import com.ml.springbootml.entities.Position;
import com.ml.springbootml.entities.Satellite;
import com.ml.springbootml.entities.SatelliteContainer;
import com.ml.springbootml.exceptions.SatelliteException;
import com.ml.springbootml.services.LocationService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LocationServiceMLTest {
    @Autowired
    LocationService locationServiceTest;

    @Test
	public void testGetLocationCorrect () throws SatelliteException {
		Satellite satellite1 = new Satellite();
        Satellite satellite2 = new Satellite();
        Satellite satellite3 = new Satellite();
        
        satellite1.setName("kenobi");
        satellite1.setDistance((float)640.31);
        satellite1.setPosition(new Position(-500,-200));
        satellite2.setName("skywalker");
        satellite2.setDistance((float)447.21);
        satellite2.setPosition(new Position(100,-100));
        satellite3.setName("sato");
        satellite3.setDistance((float)632.45);
        satellite3.setPosition(new Position(500,100));

        SatelliteContainer satellites = new SatelliteContainer();
        satellites.setSatellite(satellite1);
        satellites.setSatellite(satellite2);
        satellites.setSatellite(satellite3);
        
        Position position = new Position(-100, 300);
        Position positionReceived = locationServiceTest.getLocation(satellites);
        assertEquals((int)position.getX() + "," + (int)position.getY(), (int)positionReceived.getX() + "," + (int)positionReceived.getY());
	}
    @Test
	public void testGetLocation2Sats () {
		Satellite satellite1 = new Satellite();
        Satellite satellite2 = new Satellite();
        Satellite satellite3 = new Satellite();
        
        satellite1.setName("kenobi");
        satellite1.setDistance((float)640.31);
        satellite1.setPosition(new Position(-500,-200));
        satellite2.setName("skywalker");
        satellite2.setDistance((float)447.21);
        satellite2.setPosition(new Position(100,-100));
        satellite3.setName("sato");
        satellite3.setDistance((float)632.45);
        satellite3.setPosition(new Position(500,100));

        SatelliteContainer satellites = new SatelliteContainer();
        satellites.setSatellite(satellite1);
        satellites.setSatellite(satellite2);
        //satellites.setSatellite(satellite3);
        
        //Position position = new Position(-100, 300);
        
        try {
            Position positionReceived = locationServiceTest.getLocation(satellites);
        } catch (SatelliteException e) {
            assertEquals("La cantidad de satélites tiene que ser de 3", e.getMessage());
        }
	}
    @Test
	public void testGetLocation4Sats () {
		Satellite satellite1 = new Satellite();
        Satellite satellite2 = new Satellite();
        Satellite satellite3 = new Satellite();
        Satellite satellite4 = new Satellite();
        
        satellite1.setName("kenobi");
        satellite1.setDistance((float)640.31);
        satellite1.setPosition(new Position(-500,-200));
        satellite2.setName("skywalker");
        satellite2.setDistance((float)447.21);
        satellite2.setPosition(new Position(100,-100));
        satellite3.setName("sato");
        satellite3.setDistance((float)632.45);
        satellite3.setPosition(new Position(500,100));
        satellite4.setName("satoo");
        satellite4.setDistance((float)632.45);
        satellite4.setPosition(new Position(500,100));

        SatelliteContainer satellites = new SatelliteContainer();
        satellites.setSatellite(satellite1);
        satellites.setSatellite(satellite2);
        satellites.setSatellite(satellite3);
        satellites.setSatellite(satellite4);
        
        //Position position = new Position(-100, 300);
        
        try {
            Position positionReceived = locationServiceTest.getLocation(satellites);
        } catch (SatelliteException e) {
            assertEquals("La cantidad de satélites tiene que ser de 3", e.getMessage());
        }
	}
}
