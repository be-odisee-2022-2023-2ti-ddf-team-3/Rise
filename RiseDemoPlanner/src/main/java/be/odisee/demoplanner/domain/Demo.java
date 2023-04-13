package be.odisee.demoplanner.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.IndexColumn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="demo")
public class Demo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // HV 201804015 tbv Gebruik AutoIncrement
    private int id;

    @Column
    private String naam;

    @Column
    private String adres;

    @Column
    private String status;

    @OneToMany(mappedBy="demo")
//    @JoinColumn(name="datum_id",referencedColumnName="id")
    private List<Datum> datums;

public Demo(){

    }
    public Demo( String naam,  String adres, String status) {
        this.naam = naam;
        this.adres = adres;
        this.status = status;
    }
    public Demo( String naam,  String adres, String status, List<Datum> datums ) {
        this.naam = naam;
        this.adres = adres;
        this.status = status;
        this.datums = datums;

    }

    public Demo( int id, String naam,  String adres, String status, List<Datum> datums) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
        this.status = status;
        this.datums = datums;

    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getAdres() {
        return adres;
    }

    public String getStatus() {
        return status;
    }
    public List<Datum> getDatums() {
        return datums;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public void getDatums(List<Datum> datums) {
//        this.datums = datums;
//    }


}
