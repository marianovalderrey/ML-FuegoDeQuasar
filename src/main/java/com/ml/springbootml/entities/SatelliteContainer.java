package com.ml.springbootml.entities;

import java.util.ArrayList;
import java.util.List;

public class SatelliteContainer {

    //@Autowired
    //private Environment environment;
	
    private List<Satellite> satellites;
    
    public SatelliteContainer (){
        satellites = new ArrayList<Satellite>();
    }
    public List<Satellite> getSatellites(){
        return this.satellites;
    }
    
    public void setSatellite(Satellite satellite){
        this.satellites.add(satellite);
    }
    
    public List<List<String>> getMessages(){
        List<List<String>> messages = new ArrayList<List<String>>();
        
        for(Satellite satellite : this.satellites){
            messages.add(satellite.getMessage());
        }

        return  messages;
    }
    /*public void loadSatellitesPosition (){
        int countSat = Integer.parseInt(environment.getProperty("satellites.count"));
        
        Map<String, String> satellitesMap = new HashMap<String, String>();
        for (int i = 0; i < countSat; i++){
            satellitesMap.put(environment.getProperty("satellites."+i+".name"), environment.getProperty("satellites."+i+".position"));
        }
        for (Satellite satellite: this.satellites) {
			satellite.setPosition(new Position(satellitesMap.get(satellite.getName())));
		}
    }*/
}