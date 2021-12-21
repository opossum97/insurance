package com.example.insurance;

public class Vidi {
    private Integer id;
    private String name;
    private Integer price;
    private Integer numFamily;
    private Integer uslugaId;
    private Integer number;

    public Vidi(Integer id, String name, Integer price, Integer numFamily, Integer uslugaId, Integer number) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.numFamily = numFamily;
        this.uslugaId = uslugaId;
        this.number = number;
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

    public Integer getNumber() {return this.number;}

    public void  setNumber(Integer number) {this.number = number;}

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNumFamily() {
        return this.numFamily;
    }

    public void setNumFamily(Integer numFamily) {
        this.numFamily = numFamily;
    }

    public Integer getUslugaId() {
        return this.uslugaId;
    }

    public void setUslugaId(Integer uslugaId) {
        this.uslugaId = uslugaId;
    }
}
