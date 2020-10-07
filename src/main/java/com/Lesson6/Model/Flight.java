package com.Lesson6.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "FLIGHT")
public class Flight {
    private Long id;
    private Plane plane;
    private List passenger;
    private Date dateFlight;
    private String cityFrom;
    private String cityTo;

    public Flight() {
    }

    @Id
    @SequenceGenerator(name = "FLGHT_SEQ", sequenceName = "FLIGHT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLGHT_SEQ")
    @Column(name = "ID")
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PLANE_ID", nullable = false)
    public Plane getPlane() {
        return plane;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "FLIGHT_PASSENGER", joinColumns = @JoinColumn(name = "FLIGHT_ID"), inverseJoinColumns = @JoinColumn(name = "PASSENGER_ID"))
    public List<Passenger> getPassenger() {
        return passenger;
    }

    @Column(name = "DATE_FLIGHT")
    public Date getDateFlight() {
        return dateFlight;
    }

    @Column(name = "CITY_FROM")
    public String getCityFrom() {
        return cityFrom;
    }

    @Column(name = "CITY_TO")
    public String getCityTo() {
        return cityTo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void setPassenger(List passenger) {
        this.passenger = passenger;
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = dateFlight;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", plane=" + plane +
                ", passenger=" + passenger +
                ", dateFlight=" + dateFlight +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                '}';
    }
}