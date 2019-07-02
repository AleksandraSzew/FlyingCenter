package com.flight.demo.DBase;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateDepartue")
    private LocalDate dateDepartue;

    @Column(name = "timeDepartue")
    private String timeDepartue;

    @Column(name = "dateArrival")
    private LocalDate dateArrival;

    @Column(name = "timeArrival")
    private String timeArrival;

    @Column(name = "seats")
    private int seats;

    @Column(name = "price")
    private float price;

    @Column(name = "availableSeats")
    private int availableSeats;

    @ManyToMany
    @JoinTable(name = "flight_tourists", joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "tourist_id"))
    private Set<Tourist> tourists = new HashSet<>();

    // TODO: 2019-06-27 dodac liste turystow
    public Flight(){

    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Flight(LocalDate dateDepartue, String timeDepartue, LocalDate dateArrival, String timeArrival, int seats, float price) {
        this.dateDepartue = dateDepartue;
        this.timeDepartue = timeDepartue;
        this.dateArrival = dateArrival;
        this.timeArrival = timeArrival;
        this.seats = seats;
        this.price = price;
        this.availableSeats = seats;
    }

    public String getTimeDepartue() {
        return timeDepartue;
    }

    public void setTimeDepartue(String timeDepartue) {
        this.timeDepartue = timeDepartue;
    }

    public String getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(String timeArrival) {
        this.timeArrival = timeArrival;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDepartue() {
        return dateDepartue;
    }

    public void setDateDepartue(LocalDate dateAndTimeDepartue) {
        this.dateDepartue = dateAndTimeDepartue;
    }

    public LocalDate getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(LocalDate dateAndTimeArrival) {
        this.dateArrival = dateAndTimeArrival;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id.equals(flight.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //Tourists
    public Set<Tourist> getTourists() {
        return tourists;
    }
    public void addTourist(Tourist addedtourist){tourists.add(addedtourist);}

    public void setTourists(Set<Tourist> tourists) {
        this.tourists = tourists;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", dateDepartue=" + dateDepartue +
                ", timeDepartue='" + timeDepartue + '\'' +
                ", dateArrival=" + dateArrival +
                ", timeArrival='" + timeArrival + '\'' +
                ", seats=" + seats +
                ", price=" + price +
                ", availableSeats=" + availableSeats +
                "tourists=" + tourists +
                '}';
    }
}
