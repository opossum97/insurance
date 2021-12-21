package com.example.insurance;

public class Usluga {
    private Integer id;
    private String name;
    private String place;

    public Usluga(Integer id, String name, String place) {
        this.id = id;
        this.name = name;
        this.place = place;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {return this.name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
