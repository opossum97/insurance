package com.example.insurance;

import java.util.Date;

public class Contract {
    private Integer id;
    private String name;
    private Integer polzovanie;
    private Integer price;
    private Integer oplata;
    private Date dateZacl;
    private Date dateOcon;
    private Integer vidId;
    private Integer clientId;

    public Contract(Integer id, String name, Integer polzovanie, Integer price, Integer oplata, Date dateZacl, Date dateOcon,
                    Integer vidId, Integer clientId) {
        this.id = id;
        this.name = name;
        this.polzovanie = polzovanie;
        this.price = price;
        this.oplata = oplata;
        this.dateZacl = dateZacl;
        this.dateOcon = dateOcon;
        this.vidId = vidId;
        this.clientId = clientId;
    }

    public Integer getId() {return this.id;}
    public void setId(Integer id) {this.id = id;}

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public Integer getPolzovanie() {return this.polzovanie;}
    public void  setPolzovanie(Integer polzovanie) {this.polzovanie = polzovanie;}

    public Integer getPrice() {return this.price;}
    public void setPrice(Integer price) {this.price = price;}

    public Integer getOplata() {return this.oplata;}
    public void  setOplata(Integer oplata) {this.oplata = oplata;}

    public Date getDateZacl() {return this.dateZacl;}
    public void setDateZacl(Date dateZacl) {this.dateZacl = dateOcon;}

    public Date getDateOcon() {return this.dateOcon;}
    public void setDateOcon(Date dateOcon) {this.dateOcon = dateOcon;}

    public Integer getVidId() {
        return this.vidId;
    }
    public void setVidId(Integer vidId) {
        this.vidId = vidId;
    }

    public Integer getClientId() {return this.clientId;}
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
