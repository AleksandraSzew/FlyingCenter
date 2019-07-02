package com.flight.demo.DBase;

import com.flight.demo.DBase.Repository.FlightRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class  FlightManager {
    private FlightRepo flightRepo;
    private List<Flight>  flightList= new ArrayList<Flight>();


    public FlightManager(FlightRepo flightRepo) {
        this.flightRepo = flightRepo;
    }
    @GetMapping("/FlyingCenter")
    @CrossOrigin(origins = "http://localhost:4200")
    public Iterable<Flight> findAll () {

        return flightRepo.findAll();

    }
    public Flight save(Flight flight) {
        flightList.add(flight);
        return flightRepo.save(flight);

    }
    public void delete(Long id) {
        flightRepo.deleteById(id);
        flightList.removeIf(element -> element.getId().equals(id));
    }

}
