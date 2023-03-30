package be.odisee.demoplanner.domain;

import jakarta.persistence.*;

import java.io.Serializable;

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

    public Demo(){

    }
    public Demo( String naam,  String adres, String status) {
        this.naam = naam;
        this.adres = adres;
        this.status = status;
    }

    public Demo( int id, String naam,  String adres, String status) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
        this.status = status;
    }
}
