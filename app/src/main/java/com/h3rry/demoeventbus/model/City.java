package com.h3rry.demoeventbus.model;

/**
 * Created by herry on 15/03/2016.
 */
public class City {

    private String name;
    private Double longitude;
    private Double latitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "latitude=" + latitude +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                '}';
    }

}
