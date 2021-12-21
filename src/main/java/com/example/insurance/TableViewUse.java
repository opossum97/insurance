package com.example.insurance;

import java.util.Date;

public class TableViewUse {
    private Integer counter;
    private Integer idVida;
    private String name;
    private Integer price;
    private Integer numFamily;
    private Integer number;
    private Integer idUslugi;
    private String nameUslugi;
    private String place;
    private Integer contractId;
    private Integer polzovanie;
    private Integer oplata;
    private Date dateZacl;
    private Date dateOcon;

    public TableViewUse(Integer counter, Integer idVida, String name, Integer price, Integer numFamily, Integer number,
                     Integer idUslugi, String nameUslugi, String place, Integer contractId, Integer polzovanie, Integer oplata, Date dateZacl, Date dateOcon) {
        this.counter = counter;
        this.idVida = idVida;
        this.name = name;
        this.price = price;
        this.numFamily = numFamily;
        this.number = number;
        this.nameUslugi = nameUslugi;
        this.place = place;
        this.idUslugi = idUslugi;
        this.contractId = contractId;
        this.polzovanie = polzovanie;
        this.oplata = oplata;
        this.dateZacl = dateZacl;
        this.dateOcon = dateOcon;
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

    public Integer getContractId() {return this.contractId;}
    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getPolzovanie() {return this.polzovanie;}
    public void  setPolzovanie(Integer polzovanie) {this.polzovanie = polzovanie;}

    public Integer getOplata() {return this.oplata;}
    public void  setOplata(Integer oplata) {this.oplata = oplata;}

    public Date getDateZacl() {return this.dateZacl;}
    public void setDateZacl(Date dateZacl) {this.dateZacl = dateOcon;}

    public Date getDateOcon() {return this.dateOcon;}
    public void setDateOcon(Date dateOcon) {this.dateOcon = dateOcon;}
}
