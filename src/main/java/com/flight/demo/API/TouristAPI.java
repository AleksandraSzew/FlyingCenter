package com.flight.demo.API;

import com.flight.demo.DBase.Repository.TouristRepo;
import com.flight.demo.DBase.Tourist;
import com.flight.demo.DBase.TouristManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TouristAPI {

    @Autowired
    TouristRepo repo;

    @GetMapping("/tourists")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Tourist> getAllCustomers() {
        System.out.println("Get all Customers...");

        List<Tourist> tourists = new ArrayList<>();
        repo.findAll().forEach(tourists::add);

        return tourists;
    }

    @PostMapping(value = "/tourists/create")
    public Tourist postCustomer(@RequestBody Tourist customer) {

        Tourist _tourist = repo.save(new Tourist(customer.getFirstName(), customer.getLastName(),customer.getSex(),customer.getCountry(),customer.getNote(),customer.getBirthDate()));
        return _tourist;
    }

    @DeleteMapping("/tourists/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {
        System.out.println("Delete Customer with ID = " + id + "...");

        repo.deleteById(id);

        return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/tourists/delete")
    public ResponseEntity<String> deleteAllCustomers() {
        System.out.println("Usuwanie turystow...");

        repo.deleteAll();

        return new ResponseEntity<>("Lista turstow usunieta!", HttpStatus.OK);
    }


    @PutMapping("/tourists/{id}")
    public ResponseEntity<Tourist> updateCustomer(@PathVariable("id") long id, @RequestBody Tourist tourist) {
        System.out.println("Update Customer with ID = " + id + "...");

        Optional<Tourist> customerData = repo.findById(id);

        if (customerData.isPresent()) {
            Tourist _tourist = customerData.get();
            _tourist.setFirstName(tourist.getFirstName());
            _tourist.setLastName(tourist.getLastName());
            _tourist.setSex(tourist.getSex());
            _tourist.setCountry(tourist.getCountry());
            _tourist.setNote(tourist.getNote());
            _tourist.setBirthDate(tourist.getBirthDate());
            return new ResponseEntity<>(repo.save(_tourist), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
