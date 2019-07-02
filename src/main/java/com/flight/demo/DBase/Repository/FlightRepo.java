package com.flight.demo.DBase.Repository;

import com.flight.demo.DBase.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface FlightRepo extends CrudRepository<Flight,Long> {
}
