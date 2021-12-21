package com.example.insurance;

public class TableView {
    private Integer counter;
    private Integer idVida;
    private String name;
    private Integer price;
    private Integer numFamily;
    private Integer number;
    private Integer idUslugi;
    private String nameUslugi;
    private String place;

    public TableView(Integer counter, Integer idVida, String name, Integer price, Integer numFamily, Integer number,
                     Integer idUslugi, String nameUslugi, String place) {
        this.counter = counter;
        this.idVida = idVida;
        this.name = name;
        this.price = price;
        this.numFamily = numFamily;
        this.number = number;
        this.nameUslugi = nameUslugi;
        this.place = place;
        this.idUslugi = idUslugi;
    }

    public Integer getCounter() {return this.counter;}

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Integer getIdVida() {
        return this.idVida;
    }

    public void setIdVida(Integer idVida) {
        this.idVida = idVida;
    }

    public String getName() {return this.name;}

    public void setName(String name) {
        this.name = name;
    }

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

    public Integer getNumber() {return this.number;}

    public void  setNumber(Integer number) {this.number = number;}

    public Integer getIdUslugi() {
        return this.idUslugi;
    }

    public void setIdUslugi(Integer idUslugi) {
        this.idUslugi = idUslugi;
    }

    public String getNameUslugi() {return this.nameUslugi;}

    public void setNameUslugi(String nameUslugi) {
        this.nameUslugi = nameUslugi;
    }

    public String getPlace() {return this.place;}

    public void setPlace(String place) {
        this.place = place;
    }
}
