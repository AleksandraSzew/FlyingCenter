package com.flight.demo.DBase;

import com.flight.demo.DBase.Flight;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Tourist")
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "country")
    private String country;

    @Column(name = "note")
    private String note;

    @Column(name = "birthDate")
    private LocalDate birthDate;
// TODO: 2019-06-27  dodac liste lotow

    @ManyToMany(mappedBy = "tourists")
    private Set<Flight> flights  = new HashSet<>();


    public Long getId() {
        return id;
    }

    public Tourist(){

    }
    public Tourist(String firstName, String lastName, Sex sex, String country, String note, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.country = country;
        this.note = note;
        this.birthDate = birthDate;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Tourist{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", sex=" + sex +
                    ", country='" + country + '\'' +
                    ", note='" + note + '\'' +
                    ", birthDate=" + birthDate +
                    "flights=" + flights +
                    '}';
    }

    //Flights
    public Set<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight addedFlight){flights.add(addedFlight);}

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return id.equals(tourist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
