package com.Lesson6.Model;

import java.util.Date;

public class Filter {

    private Date DateFrom;
    private Date DateTo;
    private String cityFrom;
    private String cityTo;
    private String model;

    public Filter(Date dateFrom, Date dateTo, String cityFrom, String cityTo, String model) {
        DateFrom = dateFrom;
        DateTo = dateTo;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.model = model;
    }

    public Date getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        DateFrom = dateFrom;
    }

    public Date getDateTo() {
        return DateTo;
    }

    public void setDateTo(Date dateTo) {
        DateTo = dateTo;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
