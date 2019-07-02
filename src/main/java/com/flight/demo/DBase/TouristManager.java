package com.flight.demo.DBase;

import com.flight.demo.DBase.Repository.TouristRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TouristManager {

    private TouristRepo touristRepo;

    public List<Tourist> touristsList = new ArrayList<Tourist>();

    public TouristManager(TouristRepo touristRepo) {
        this.touristRepo = touristRepo;
    }
    @GetMapping("/FlyingCenter")
    @CrossOrigin(origins = "http://localhost:4200")
    public Iterable<Tourist> findAll () {

        return touristRepo.findAll();

    }
    public Tourist save(Tourist tourist) {
        touristsList.add(tourist);
        return touristRepo.save(tourist);

    }
    public void delete(Long id) {
        touristRepo.deleteById(id);
        touristsList.removeIf(element -> element.getId().equals(id));
    }
    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){

    }
}
