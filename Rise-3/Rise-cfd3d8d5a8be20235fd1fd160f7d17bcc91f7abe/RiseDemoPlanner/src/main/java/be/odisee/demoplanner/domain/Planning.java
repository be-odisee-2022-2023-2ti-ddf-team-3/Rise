package be.odisee.demoplanner.domain;

import jakarta.persistence.*;

import java.io.Serializable;

// @Index bij een veld is deprecated,
// jakarta.persistence.Index is te gebruiken en
// die moet als deel van @Table worden meegegeven
@Entity
@Table(name="planning")
public class Planning implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // HV 201804015 tbv Gebruik AutoIncrement
    private int id;

    @Column
    private String status;

    @Column
    private int datum_id;
    @Column
    private int ambassadeur_id;
    public  Planning(){}

    public Planning(String status, int datum_id, int ambassadeur_id) {
        this.status = status;
        this.datum_id = datum_id;
        this.ambassadeur_id = ambassadeur_id;
    }

    public Planning(int id, String status, int datum_id, int ambassadeur_id) {
        this.id = id;
        this.status = status;
        this.datum_id = datum_id;
        this.ambassadeur_id = ambassadeur_id;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getDatum_id() {
        return datum_id;
    }

    public int getAmbassadeur_id() {
        return ambassadeur_id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setDatum_id(int datum_id) {this.datum_id = datum_id;}
    public void setAmbassadeur_id(int ambassadeur_id) {
        this.ambassadeur_id = ambassadeur_id;
    }

}
