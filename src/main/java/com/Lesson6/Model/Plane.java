package com.Lesson6.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "PLANE")
public class Plane {
    private Long id;
    private String model;
    private String code;
    private int yearProduced;
    private Double avgFuelConsumption;

    public Plane() {
    }

    @Id
    @SequenceGenerator(name = "PL_SEQ", sequenceName = "PLANE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PL_SEQ")
    @Column(name = "ID")
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @Column(name = "MODEL")
    public String getModel() {
        return model;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    @Column(name = "YEAR_PRODUCED")
    public int getYearProduced() {
        return yearProduced;
    }

    @Column(name = "AVG_FUEL_CONSUMPTION")
    public Double getAvgFuelConsumption() {
        return avgFuelConsumption;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setYearProduced(int yearProduced) {
        this.yearProduced = yearProduced;
    }

    public void setAvgFuelConsumption(Double avgFuelConsumption) {
        this.avgFuelConsumption = avgFuelConsumption;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", code='" + code + '\'' +
                ", yearProduced=" + yearProduced +
                ", avgFuelConsumption=" + avgFuelConsumption +
                '}';
    }
}