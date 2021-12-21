package com.example.insurance;

import java.util.Date;

public class TableViewClient {
    private Integer counter;
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer polzovanie;
    private Integer contractId;
    private String name;
    private Integer price;
    private Integer oplata;
    private Date dateZacl;
    private Date dateOcon;

    public TableViewClient(Integer counter, Integer id, String firstName, String lastName, Integer polzovanie,
                           Integer contractId, String name, Integer price, Integer oplata, Date dateZacl, Date dateOcon) {
        this.counter = counter;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.polzovanie = polzovanie;
        this.contractId = contractId;
        this.name = name;
        this.price = price;
        this.oplata = oplata;
        this.dateZacl = dateZacl;
        this.dateOcon = dateOcon;
    }
    public Integer getCounter() {return this.counter;}

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Integer getId() {return this.id;}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {return this.lastName;}

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPolzovanie() {return this.polzovanie;}

    public void  setPolzovanie(Integer polzovanie) {this.polzovanie = polzovanie;}

    public Integer getContractId() {return this.contractId;}

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getName() {return this.name;}

    public void setName(String name) {this.name = name;}

    public Integer getPrice() {return this.price;}

    public void setPrice(Integer price) {this.price = price;}

    public Integer getOplata() {return this.oplata;}

    public void  setOplata(Integer oplata) {this.oplata = oplata;}

    public Date getDateZacl() {return this.dateZacl;}

    public void setDateZacl(Date dateZacl) {this.dateZacl = dateOcon;}

    public Date getDateOcon() {return this.dateOcon;}

    public void setDateOcon(Date dateOcon) {this.dateOcon = dateOcon;}
}
