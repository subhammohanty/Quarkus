package org.hprtech.main;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Startup
public class CarService {

    @PostConstruct
    public void startEngine(){
        System.out.println("Engine Started : {}");
    }

    @PreDestroy
    public void stopEngine(){
        System.out.println("Engine Stopped : {}");
    }
}
