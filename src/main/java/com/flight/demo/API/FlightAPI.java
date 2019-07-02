package com.flight.demo.API;

import com.flight.demo.DBase.Flight;
import com.flight.demo.DBase.Repository.FlightRepo;
import com.flight.demo.DBase.Repository.TouristRepo;
import com.flight.demo.DBase.Tourist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FlightAPI {

    @Autowired
    FlightRepo repo;

    @GetMapping("/flights")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Flight> getAllFlights() {
        System.out.println("All flights");

        List<Flight> flights = new ArrayList<>();
        repo.findAll().forEach(flights::add);

        return flights;
    }

    @PostMapping(value = "/flights/create")
    public Flight postFlight(@RequestBody Flight flight) {

        Flight _flight = repo.save(new Flight(flight.getDateDepartue(), flight.getTimeDepartue(),flight.getDateArrival(),flight.getTimeArrival(),flight.getSeats(),flight.getPrice()));
        return _flight;
    }

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable("id") long id) {
        System.out.println("Usun lot = " + id + "...");

        repo.deleteById(id);

        return new ResponseEntity<>("Flights has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/flights/delete")
    public ResponseEntity<String> deleteAllFlights() {
        System.out.println("Deleting flights...");

        repo.deleteAll();

        return new ResponseEntity<>("List of flights deleted!", HttpStatus.OK);
    }

    @GetMapping("/flights/{id}/tourists")
    public  List<Tourist> getTouristOfFlight(@PathVariable("id") long id, @RequestBody Flight flight){
        System.out.println("Getting tourist of flight with ID = " + id + "...");
        List<Tourist> tourists = new ArrayList<>();
        repo.findAll().forEach((Consumer<? super Flight>) tourists);
        return tourists;
    }
    @PutMapping("/flights/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable("id") long id, @RequestBody Flight flight) {
        System.out.println("Update Flight with ID = " + id + "...");

        Optional<Flight> flightData = repo.findById(id);

        if (flightData.isPresent()) {
            Flight _flight = flightData.get();
            _flight.setDateDepartue(flight.getDateDepartue());
            _flight.setTimeDepartue(flight.getTimeDepartue());
            _flight.setDateArrival(flight.getDateArrival());
            _flight.setTimeArrival(flight.getTimeArrival());
            _flight.setSeats(flight.getSeats());
            _flight.setPrice(flight.getPrice());
            _flight.setAvailableSeats(flight.getSeats());
            return new ResponseEntity<>(repo.save(_flight), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
