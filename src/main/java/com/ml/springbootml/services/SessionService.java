package com.ml.springbootml.services;

import javax.servlet.http.HttpSession;

import com.ml.springbootml.entities.Satellite;
import com.ml.springbootml.entities.SatelliteContainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    private Environment environment;
	
    public void setSatellite (Satellite satellite, HttpSession session){
        session.setAttribute(satellite.getName(), satellite);
    }
    public SatelliteContainer getSatelliteContainer(HttpSession session){
        SatelliteContainer satellites = new SatelliteContainer();
        for(int i = 0; i< Integer.parseInt(environment.getProperty("satellites.count")); i++){
            Satellite satellite = (Satellite)session.getAttribute(environment.getProperty("satellites."+i+".name"));
            if (satellite != null){
                satellites.setSatellite(satellite);
            }
        }
        if (satellites.getSatellites().size() == Integer.parseInt(environment.getProperty("satellites.count"))){
            session.invalidate();
        }
        return satellites;
    }
    public int getSatellitesSize(HttpSession session){
        int size = 0;
        for(int i = 0; i< Integer.parseInt(environment.getProperty("satellites.count")); i++){
            Satellite satellite = (Satellite)session.getAttribute(environment.getProperty("satellites."+i+".name"));
            if (satellite != null){
                size++;
            }
        }
        return size;
    }
    public boolean informationComplete(HttpSession session){
        int size = 0;
        for(int i = 0; i< Integer.parseInt(environment.getProperty("satellites.count")); i++){
            Satellite satellite = (Satellite)session.getAttribute(environment.getProperty("satellites."+i+".name"));
            if (satellite != null){
                size++;
            }
        }
        return Integer.parseInt(environment.getProperty("satellites.count")) == size ? true : false;
    }
    
}
